package com.mcoding.base.product.service.productSet.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productSet.ProductSet;
import com.mcoding.base.product.bean.productSet.ProductSetExample;
import com.mcoding.base.product.persistence.productSet.ProductSetMapper;
import com.mcoding.base.product.service.productSet.ProductSetService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("productSetService")
public class ProductSetServiceImpl implements ProductSetService {
    @Resource
    protected ProductSetMapper productSetMapper;

    @CacheEvict(value={"productSet"}, allEntries=true)
    @Override
    public void addObj(ProductSet t) {
        this.productSetMapper.insertSelective(t);
    }

    @CacheEvict(value={"productSet"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.productSetMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"productSet"}, allEntries=true)
    @Override
    public void modifyObj(ProductSet t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productSetMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="productSet", key="'ProductSetService_' + #root.methodName + '_' +#id")
    @Override
    public ProductSet queryObjById(int id) {
        return this.productSetMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="productSet", key="'ProductSetService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ProductSet> queryAllObjByExample(ProductSetExample example) {
        return this.productSetMapper.selectByExample(example);
    }

    @Cacheable(value="productSet", key="'ProductSetService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ProductSet> queryObjByPage(ProductSetExample example) {
        PageView<ProductSet> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productSetMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="productSet", key="'ProductSetService_' + #root.methodName + '_'+ #setId")
	@Override
	public List<ProductSet> queryByProductId(int setId) {
    	ProductSetExample example = new ProductSetExample();
    	example.createCriteria().andSetIdEqualTo(setId);
    	
		return this.productSetMapper.selectByExample(example);
	}
}