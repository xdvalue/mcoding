package com.mcoding.base.product.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import com.mcoding.base.product.bean.product.ProductImgExample;
import com.mcoding.base.product.bean.productPoint.ProductPointExample;
import com.mcoding.base.product.bean.productPrice.ProductPrice;
import com.mcoding.base.product.bean.productPrice.ProductPriceExample;
import com.mcoding.base.product.persistence.product.ProductImgMapper;
import com.mcoding.base.product.persistence.productPoint.ProductPointMapper;
import com.mcoding.base.product.persistence.productPrice.ProductPriceMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.product.Product;
import com.mcoding.base.product.bean.product.ProductExample;
import com.mcoding.base.product.persistence.product.ProductMapper;
import com.mcoding.base.product.service.product.ProductImgService;
import com.mcoding.base.product.service.product.ProductService;
import com.mcoding.base.product.service.productPrice.ProductPriceService;
import com.mcoding.base.product.service.productSet.ProductSetService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    protected ProductMapper productMapper;
    @Autowired
    protected ProductPriceMapper productPriceMapper;
    @Autowired
    protected ProductImgMapper productImgMapper;
    @Autowired
    protected ProductPriceService productPriceService;
    
    @Autowired
    protected ProductImgService productImgService;
    
    @Autowired
    protected ProductSetService productSetService;

    @Autowired
    protected ProductPointMapper productPointMapper;



    @CacheEvict(value={"product"}, allEntries=true)
    @Override
    public void addObj(Product t) {
        this.productMapper.insertSelective(t);
    }

    @CacheEvict(value={"product"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {

        this.productMapper.deleteByPrimaryKey(id);
        ProductPriceExample priceExample = new ProductPriceExample();
        ProductPriceExample.Criteria criteria = priceExample.createCriteria();
        criteria.andProductIdEqualTo(id);
        this.productPriceMapper.deleteByExample(priceExample);

        ProductImgExample imgExample = new ProductImgExample();
        ProductImgExample.Criteria criteria1 = imgExample.createCriteria();
        criteria1.andProductIdEqualTo(id);
        this.productImgMapper.deleteByExample(imgExample);

        ProductPriceExample priceExample1 = new ProductPriceExample();
        ProductPriceExample.Criteria criteria2 = priceExample.createCriteria();
        criteria2.andProductIdEqualTo(id);
        this.productPriceMapper.deleteByExample(priceExample);

        ProductPointExample pointExample = new ProductPointExample();
        ProductPointExample.Criteria criteria3 = pointExample.createCriteria();
        criteria3.andProductIdEqualTo(id);
        this.productPointMapper.deleteByExample(pointExample);
    }

    @CacheEvict(value={"product"}, allEntries=true)
    @Override
    public void modifyObj(Product t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="product", key="'ProductService_' + #root.methodName + '_' +#id")
    @Override
    public Product queryObjById(int id) {
        return this.productMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="product", key="'ProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<Product> queryAllObjByExample(ProductExample example) {
        return this.productMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable(value="product", key="'ProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<Product> queryObjByPage(ProductExample example) {
        PageView<Product> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="product", key="'ProductService_' + #root.methodName + '_'+ #example.toJson() + '_' + #categoryId")
    @Override
    public PageView<Product> findConditionByPage(ProductExample example,Integer categoryId) {
    	if (categoryId==null || categoryId <= 0) {
			return this.queryObjByPage(example);
		}
    	
    	PageView<Product> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productMapper.findConditionByPage(example, categoryId));
    	return pageView;
    }

    @Override
    public PageView<Product> findConditionByPage(ProductExample example,Integer categoryId, String sceneCode) {
    	PageView<Product> pageView = SpringContextHolder.getOneBean(ProductService.class).findConditionByPage(example, categoryId);
    	
    	List<Product> productList = pageView.getQueryResult();
    	for(int i=0; CollectionUtils.isNotEmpty(productList) && i<productList.size(); i++){
    		Product product = productList.get(i);
    		this.setDataInProduct(product, sceneCode);
    	}
    	
    	return pageView;
    }
    
    @Override
    public Product queryProductDetailById(String sceneCode, int id) {
    	Product product = SpringContextHolder.getOneBean(ProductService.class).queryObjById(id);
    	this.setDataInProduct(product, sceneCode);
    	return product;
    }
    
    private void setDataInProduct(Product product, String sceneCode){
    	if (product == null || product.getId() == null || product.getId() ==0) {
			return;
		}
		try {
			product.setPriceList(this.productPriceService.findProductPriceByScene(sceneCode, product.getId()));
			product.setImgList(this.productImgService.queryByProductId(product.getId()));
			
			if (Constant.YES_INT.equals(product.getSetStatus())) {
				//如果是套餐就把套餐底下的产品查询出来
				product.setProductSet(this.productSetService.queryByProductId(product.getId()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}