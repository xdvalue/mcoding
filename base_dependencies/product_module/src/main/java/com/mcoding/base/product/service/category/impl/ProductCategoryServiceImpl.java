package com.mcoding.base.product.service.category.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.category.ProductCategory;
import com.mcoding.base.product.bean.category.ProductCategoryExample;
import com.mcoding.base.product.persistence.category.ProductCategoryMapper;
import com.mcoding.base.product.service.category.ProductCategoryService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    protected ProductCategoryMapper productCategoryMapper;

    @CacheEvict(value={"productCategory"}, allEntries=true)
    @Override
    public void addObj(ProductCategory t) {
        this.productCategoryMapper.insertSelective(t);
    }

    @CacheEvict(value={"productCategory"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.productCategoryMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"productCategory"}, allEntries=true)
    @Override
    public void modifyObj(ProductCategory t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productCategoryMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="productCategory", key="'ProductCategoryService_' + #root.methodName + '_' +#id")
    @Override
    public ProductCategory queryObjById(int id) {
        return this.productCategoryMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="productCategory", key="'ProductCategoryService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ProductCategory> queryAllObjByExample(ProductCategoryExample example) {
        return this.productCategoryMapper.selectByExample(example);
    }

    @Cacheable(value="productCategory", key="'ProductCategoryService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ProductCategory> queryObjByPage(ProductCategoryExample example) {
        PageView<ProductCategory> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productCategoryMapper.selectByExampleByPage(example));
        return pageView;
    }
    
    @Cacheable(value="productCategory", key="'ProductCategoryService_' + #root.methodName + '_' +#parentId")
	@Override
	public List<ProductCategory> queryChildern(int parentId) {
		ProductCategoryExample example = new ProductCategoryExample();
		example.createCriteria().andCategoryParentIdEqualTo(parentId);
		
		return this.queryAllObjByExample(example);
	}
}