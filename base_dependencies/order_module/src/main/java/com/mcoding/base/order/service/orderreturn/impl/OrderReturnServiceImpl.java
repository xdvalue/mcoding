package com.mcoding.base.order.service.orderreturn.impl;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.order.Order;
import com.mcoding.base.order.bean.orderreturn.OrderReturn;
import com.mcoding.base.order.bean.orderreturn.OrderReturnExample;
import com.mcoding.base.order.persistence.orderreturn.OrderReturnMapper;
import com.mcoding.base.order.service.order.OrderService;
import com.mcoding.base.order.service.orderreturn.OrderReturnService;
import com.mcoding.base.order.service.orderreturn.event.OrderReturnComfireEvent;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderReturnService")
    @Resource
    public class OrderReturnServiceImpl implements OrderReturnService {
    protected OrderReturnMapper orderReturnMapper;
    
    @Autowired
    protected OrderService orderService;

    @CacheEvict(value={"orderReturn"}, allEntries=true)
    public void addObj(OrderReturn t) {
        this.orderReturnMapper.insertSelective(t);
    }

    @CacheEvict(value={"orderReturn"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.orderReturnMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"orderReturn"}, allEntries=true)
    @Override
    public void modifyObj(OrderReturn t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.orderReturnMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="orderReturn", key="'OrderReturnService_' + #root.methodName + '_' +#id")
    @Override
    public OrderReturn queryObjById(int id) {
        return this.orderReturnMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="orderReturn", key="'OrderReturnService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<OrderReturn> queryAllObjByExample(OrderReturnExample example) {
        return this.orderReturnMapper.selectByExample(example);
    }

    @Cacheable(value="orderReturn", key="'OrderReturnService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<OrderReturn> queryObjByPage(OrderReturnExample example) {
        PageView<OrderReturn> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.orderReturnMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"orderReturn"}, allEntries=true)
    @Transactional
	@Override
	public void apply(OrderReturn orderReturn, Member member) {
		Order order = this.orderService.queryObjById(orderReturn.getOrderId());
    	if (order == null) {
			throw new CommonException("订单不存在，请刷新后重新申请");
		}
    	if (Order.PAY_STATUS_RETURNED.equals(order.getStatus())) {
			throw new CommonException("订单已经申请退货，请不用重复申请");
		}
    	if (!Order.PAY_STATUS_FINISHED.equals(order.getStatus())) {
			throw new CommonException("订单还没有确认收到货，无法申请退货");
		}
    	
    	orderReturn.setMemberId(member.getId());
    	orderReturn.setMemberName(member.getName());
    	orderReturn.setOrderNo(order.getOrderNo());
    	orderReturn.setReturnStatus(OrderReturn.STATUS_APPLY);
    	orderReturn.setReturnType(OrderReturn.TYPE_RETURN_MONEY);
    	orderReturn.setStoreId(StoreUtils.getStoreFromThreadLocal().getId());
    	orderReturn.setFee(order.getAmountPay());
    	
    	this.addObj(orderReturn);
    	
    	Order tmp = new Order();
    	tmp.setId(order.getId());
    	tmp.setStatus(Order.PAY_STATUS_RETURNED);
    	this.orderService.modifyObj(tmp);
	}

    @CacheEvict(value={"orderReturn"}, allEntries=true)
	@Override
	public void comfireAudit(int orderReturnId) {
		OrderReturn tmp = new OrderReturn();
    	tmp.setId(orderReturnId);
    	tmp.setReturnStatus(OrderReturn.STATUS_AUDIT);
    	this.modifyObj(tmp);
    	OrderReturn orderReturn = this.orderReturnMapper.selectByPrimaryKey(orderReturnId);
    	SpringContextHolder.getApplicationContext().publishEvent(new OrderReturnComfireEvent(orderReturn));
	}
}