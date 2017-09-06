package com.mcoding.base.dst.service.income.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProduct;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProductExample;
import com.mcoding.base.dst.persistence.income.DstIncomeOrderProductMapper;
import com.mcoding.base.dst.service.income.DstIncomeOrderProductService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("dstIncomeOrderProductService")
public class DstIncomeOrderProductServiceImpl implements DstIncomeOrderProductService {
    @Resource
    protected DstIncomeOrderProductMapper dstIncomeOrderProductMapper;

    @CacheEvict(value={"dstIncomeOrderProduct"}, allEntries=true)
    @Override
    public void addObj(DstIncomeOrderProduct t) {
        this.dstIncomeOrderProductMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstIncomeOrderProduct"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstIncomeOrderProductMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstIncomeOrderProduct"}, allEntries=true)
    @Override
    public void modifyObj(DstIncomeOrderProduct t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstIncomeOrderProductMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstIncomeOrderProduct", key="'DstIncomeOrderProductService_' + #root.methodName + '_' +#id")
    @Override
    public DstIncomeOrderProduct queryObjById(int id) {
        return this.dstIncomeOrderProductMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstIncomeOrderProduct", key="'DstIncomeOrderProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstIncomeOrderProduct> queryAllObjByExample(DstIncomeOrderProductExample example) {
        return this.dstIncomeOrderProductMapper.selectByExample(example);
    }

    @Cacheable(value="dstIncomeOrderProduct", key="'DstIncomeOrderProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstIncomeOrderProduct> queryObjByPage(DstIncomeOrderProductExample example) {
        PageView<DstIncomeOrderProduct> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstIncomeOrderProductMapper.selectByExampleByPage(example));
        return pageView;
    }
}