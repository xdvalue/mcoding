package com.mcoding.base.product.service.productCategoryRef.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productCategoryRef.ProductCategoryRef;
import com.mcoding.base.product.bean.productCategoryRef.ProductCategoryRefExample;
import com.mcoding.base.product.persistence.productCategoryRef.ProductCategoryRefMapper;
import com.mcoding.base.product.service.productCategoryRef.ProductCategoryRefService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("productCategoryRefService")
public class ProductCategoryRefServiceImpl implements ProductCategoryRefService {
    @Resource
    protected ProductCategoryRefMapper productCategoryRefMapper;

    @CacheEvict(value={"productCategoryRef"}, allEntries=true)
    @Override
    public void addObj(ProductCategoryRef t) {
        this.productCategoryRefMapper.insertSelective(t);
    }

    @CacheEvict(value={"productCategoryRef"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.productCategoryRefMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"productCategoryRef"}, allEntries=true)
    @Override
    public void modifyObj(ProductCategoryRef t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productCategoryRefMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="productCategoryRef", key="'ProductCategoryRefService_' + #root.methodName + '_' +#id")
    @Override
    public ProductCategoryRef queryObjById(int id) {
        return this.productCategoryRefMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="productCategoryRef", key="'ProductCategoryRefService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ProductCategoryRef> queryAllObjByExample(ProductCategoryRefExample example) {
        return this.productCategoryRefMapper.selectByExample(example);
    }

    @Cacheable(value="productCategoryRef", key="'ProductCategoryRefService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ProductCategoryRef> queryObjByPage(ProductCategoryRefExample example) {
        PageView<ProductCategoryRef> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productCategoryRefMapper.selectByExampleByPage(example));
        return pageView;
    }
}