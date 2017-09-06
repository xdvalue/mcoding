package com.mcoding.base.product.service.product.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.product.ProductImg;
import com.mcoding.base.product.bean.product.ProductImgExample;
import com.mcoding.base.product.persistence.product.ProductImgMapper;
import com.mcoding.base.product.service.product.ProductImgService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("productImgService")
public class ProductImgServiceImpl implements ProductImgService {
    @Resource
    protected ProductImgMapper productImgMapper;

    @CacheEvict(value={"productImg"}, allEntries=true)
    @Override
    public void addObj(ProductImg t) {
        this.productImgMapper.insertSelective(t);
    }

    @CacheEvict(value={"productImg"}, allEntries=true)
    @Override
    public void deleteObjByExample(ProductImgExample example) {
        this.productImgMapper.deleteByExample(example);
    }

    @CacheEvict(value={"productImg"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.productImgMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"productImg"}, allEntries=true)
    @Override
    public void modifyObj(ProductImg t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productImgMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="productImg", key="'ProductImgService_' + #root.methodName + '_' +#id")
    @Override
    public ProductImg queryObjById(int id) {
        return this.productImgMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="productImg", key="'ProductImgService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ProductImg> queryAllObjByExample(ProductImgExample example) {
        return this.productImgMapper.selectByExample(example);
    }

    @Cacheable(value="productImg", key="'ProductImgService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ProductImg> queryObjByPage(ProductImgExample example) {
        PageView<ProductImg> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productImgMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="productImg", key="'ProductImgService_' + #root.methodName + '_' +#productId")
	@Override
	public List<ProductImg> queryByProductId(int productId) {
    	ProductImgExample example = new ProductImgExample();
    	example.createCriteria().andProductIdEqualTo(productId);
    	
		return this.queryAllObjByExample(example);
	}
}