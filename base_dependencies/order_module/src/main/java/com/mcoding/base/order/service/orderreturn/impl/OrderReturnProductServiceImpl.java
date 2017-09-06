package com.mcoding.base.order.service.orderreturn.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.order.bean.orderreturn.OrderReturnProduct;
import com.mcoding.base.order.bean.orderreturn.OrderReturnProductExample;
import com.mcoding.base.order.persistence.orderreturn.OrderReturnProductMapper;
import com.mcoding.base.order.service.orderreturn.OrderReturnProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("orderReturnProductService")
public class OrderReturnProductServiceImpl implements OrderReturnProductService {
    @Resource
    protected OrderReturnProductMapper orderReturnProductMapper;

    @CacheEvict(value={"orderReturnProduct"}, allEntries=true)
    @Override
    public void addObj(OrderReturnProduct t) {
        this.orderReturnProductMapper.insertSelective(t);
    }

    @CacheEvict(value={"orderReturnProduct"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.orderReturnProductMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"orderReturnProduct"}, allEntries=true)
    @Override
    public void modifyObj(OrderReturnProduct t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.orderReturnProductMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="orderReturnProduct", key="'OrderReturnProductService_' + #root.methodName + '_' +#id")
    @Override
    public OrderReturnProduct queryObjById(int id) {
        return this.orderReturnProductMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="orderReturnProduct", key="'OrderReturnProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<OrderReturnProduct> queryAllObjByExample(OrderReturnProductExample example) {
        return this.orderReturnProductMapper.selectByExample(example);
    }

    @Cacheable(value="orderReturnProduct", key="'OrderReturnProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<OrderReturnProduct> queryObjByPage(OrderReturnProductExample example) {
        PageView<OrderReturnProduct> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.orderReturnProductMapper.selectByExampleByPage(example));
        return pageView;
    }
}