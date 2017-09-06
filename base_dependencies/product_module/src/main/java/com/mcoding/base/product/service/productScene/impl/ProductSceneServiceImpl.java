package com.mcoding.base.product.service.productScene.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.product.bean.productScene.ProductScene;
import com.mcoding.base.product.bean.productScene.ProductSceneExample;
import com.mcoding.base.product.persistence.productScene.ProductSceneMapper;
import com.mcoding.base.product.service.productScene.ProductSceneService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("productSceneService")
public class ProductSceneServiceImpl implements ProductSceneService {
    @Resource
    protected ProductSceneMapper productSceneMapper;

    @CacheEvict(value={"productScene"}, allEntries=true)
    @Override
    public void addObj(ProductScene t) {
        this.productSceneMapper.insertSelective(t);
    }

    @CacheEvict(value={"productScene"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.productSceneMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"productScene"}, allEntries=true)
    @Override
    public void modifyObj(ProductScene t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.productSceneMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="productScene", key="'ProductSceneService_' + #root.methodName + '_' +#id")
    @Override
    public ProductScene queryObjById(int id) {
        return this.productSceneMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="productScene", key="'ProductSceneService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ProductScene> queryAllObjByExample(ProductSceneExample example) {
        return this.productSceneMapper.selectByExample(example);
    }

    @Cacheable(value="productScene", key="'ProductSceneService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ProductScene> queryObjByPage(ProductSceneExample example) {
        PageView<ProductScene> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.productSceneMapper.selectByExampleByPage(example));
        return pageView;
    }
}