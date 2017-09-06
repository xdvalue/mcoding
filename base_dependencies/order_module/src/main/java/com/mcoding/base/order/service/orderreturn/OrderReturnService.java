package com.mcoding.base.order.service.orderreturn;

import java.util.List;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.order.bean.orderreturn.OrderReturn;
import com.mcoding.base.order.bean.orderreturn.OrderReturnExample;

public interface OrderReturnService {
	
//	public void addObj(OrderReturn t);
	/**
	 * 申请退货
	 * @param orderReturn
	 * @param member
	 */
	public void apply(OrderReturn orderReturn, Member member);
	
	/**
	 * 退货申请，审核通过
	 * @param orderReturn
	 */
	public void comfireAudit(int orderReturnId);

    public void deleteObjById(int id);

    public void modifyObj(OrderReturn t);

    public OrderReturn queryObjById(int id);
    
    public List<OrderReturn> queryAllObjByExample(OrderReturnExample example);

    public PageView<OrderReturn> queryObjByPage(OrderReturnExample example);
}