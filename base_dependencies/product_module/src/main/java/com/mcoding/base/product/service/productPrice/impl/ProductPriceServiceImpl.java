package com.mcoding.base.product.service.productPrice.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productPrice.ProductPriceExample;
import com.mcoding.base.product.bean.productScene.ProductScene;
import com.mcoding.base.product.bean.productScene.ProductSceneExample;
import com.mcoding.base.product.persistence.productPrice.ProductPriceMapper;
import com.mcoding.base.product.service.product.ProductService;
import com.mcoding.base.product.service.productPrice.ProductPriceRule;
import com.mcoding.base.product.service.productPrice.ProductPriceService;
import com.mcoding.base.product.service.productScene.ProductSceneService;

@Service("productPriceService")
public class ProductPriceServiceImpl implements ProductPriceService {
    @Resource
    protected ProductPriceMapper productPriceMapper;
    
    @Autowired
    protected ProductSceneService productSceneService;

    @CacheEvict(value={"productPrice"}, allEntries=true)
    @Override
    public void addObj(ProductPrice t) {
        this.productPriceMapper.insertSelective(t);
    }

    @CacheEvict(value={"productPrice"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.productPriceMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"productPrice"}, allEntries=true)
    @Override
    public void modifyObj(ProductPrice t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productPriceMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="productPrice", key="'ProductPriceService_' + #root.methodName + '_' +#id")
    @Override
    public ProductPrice queryObjById(int id) {
        return this.productPriceMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="productPrice", key="'ProductPriceService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ProductPrice> queryAllObjByExample(ProductPriceExample example) {
        return this.productPriceMapper.selectByExample(example);
    }

    @Cacheable(value="productPrice", key="'ProductPriceService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ProductPrice> queryObjByPage(ProductPriceExample example) {
        PageView<ProductPrice> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productPriceMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="productPrice", key="'ProductPriceService_' + #root.methodName + '_'+ #sceneCode + '_' + #productId")
	@Override
	public List<ProductPrice> findProductPriceByScene(String sceneCode, int productId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    	List<ProductPrice> priceList = new ArrayList<>();
    	
    	ProductSceneExample productSceneExample = new ProductSceneExample();
    	productSceneExample.createCriteria().andCodeEqualTo(sceneCode).andStatusEqualTo(Constant.YES_INT);
    	productSceneExample.or().andTypeEqualTo(ProductScene.TYPE_DEFAULT).andStatusEqualTo(Constant.YES_INT);

    	productSceneExample.setOrderByClause("priority DESC");
    	
    	List<ProductScene> sceneList = this.productSceneService.queryAllObjByExample(productSceneExample);
    	for(int i=0; CollectionUtils.isNotEmpty(sceneList) && i<sceneList.size(); i++){
    		ProductScene scene = sceneList.get(i);
    		
    		ProductPriceExample productPriceExample = new ProductPriceExample();
    		ProductPriceExample.Criteria cri = productPriceExample.createCriteria();
    		cri.andSceneIdEqualTo(scene.getId()).andProductIdEqualTo(productId);
    		
    		List<ProductPrice> tmpList = this.queryAllObjByExample(productPriceExample);
    		if(CollectionUtils.isEmpty(tmpList)){
    			continue;
    		}
    		if (StringUtils.isBlank(scene.getMethod())) {
    			priceList.add(tmpList.get(0));
    			continue;
			}
    		
    		if (!ProductPriceRule.class.isAssignableFrom(Class.forName(scene.getMethod()))){
				throw new CommonException("价格规则["+scene.getMethod()+"],无效，因为没有继承 ProductPriceRule");
			}
    		
    		ProductPriceRule rule = (ProductPriceRule) Class.forName(scene.getMethod()).getConstructor(ProductScene.class).newInstance(scene);
			
    		priceList.add(rule.handle(tmpList.get(0)));
    	}
    	
		return priceList;
	}
}