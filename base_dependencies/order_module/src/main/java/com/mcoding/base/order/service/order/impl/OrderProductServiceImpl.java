package com.mcoding.base.order.service.order.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.order.bean.order.OrderProduct;
import com.mcoding.base.order.bean.order.OrderProductExample;
import com.mcoding.base.order.persistence.order.OrderProductMapper;
import com.mcoding.base.order.service.order.OrderProductService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("orderProductService")
public class OrderProductServiceImpl implements OrderProductService {
    @Resource
    protected OrderProductMapper orderProductMapper;

    @CacheEvict(value={"orderProduct"}, allEntries=true)
    @Override
    public void addObj(OrderProduct t) {
        this.orderProductMapper.insertSelective(t);
    }

    @CacheEvict(value={"orderProduct"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.orderProductMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"orderProduct"}, allEntries=true)
    @Override
    public void modifyObj(OrderProduct t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.orderProductMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="orderProduct", key="'OrderProductService_' + #root.methodName + '_' +#id")
    @Override
    public OrderProduct queryObjById(int id) {
        return this.orderProductMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="orderProduct", key="'OrderProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<OrderProduct> queryAllObjByExample(OrderProductExample example) {
        return this.orderProductMapper.selectByExample(example);
    }

    @Cacheable(value="orderProduct", key="'OrderProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<OrderProduct> queryObjByPage(OrderProductExample example) {
        PageView<OrderProduct> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.orderProductMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="orderProduct", key="'OrderProductService_' + #root.methodName + '_'+ #orderId")
	@Override
	public List<OrderProduct> queryByOrderId(int orderId) {
    	OrderProductExample example = new OrderProductExample();
    	example.createCriteria().andOrderIdEqualTo(orderId);
    	
		return this.queryAllObjByExample(example);
	}
}