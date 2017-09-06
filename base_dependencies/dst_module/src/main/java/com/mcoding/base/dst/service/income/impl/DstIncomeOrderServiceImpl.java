package com.mcoding.base.dst.service.income.impl;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.income.DstIncomeOrder;
import com.mcoding.base.dst.bean.income.DstIncomeOrderExample;
import com.mcoding.base.dst.bean.income.DstIncomeOrderProduct;
import com.mcoding.base.dst.persistence.income.DstIncomeOrderMapper;
import com.mcoding.base.dst.service.income.DstIncomeMemberService;
import com.mcoding.base.dst.service.income.DstIncomeOrderProductService;
import com.mcoding.base.dst.service.income.DstIncomeOrderService;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.order.bean.order.Order;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dstIncomeOrderService")
public class DstIncomeOrderServiceImpl implements DstIncomeOrderService {
    @Resource
    protected DstIncomeOrderMapper dstIncomeOrderMapper;
    
    @Resource
    protected DstIncomeOrderProductService dstIncomeOrderProductService;
    
    @Resource
    protected DstIncomeMemberService dstIncomeMemberService;
    
    @Resource
    protected MemberService memberService;

    @CacheEvict(value={"dstIncomeOrder"}, allEntries=true)
    @Override
    public void addObj(DstIncomeOrder t) {
        this.dstIncomeOrderMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstIncomeOrder"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstIncomeOrderMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstIncomeOrder"}, allEntries=true)
    @Override
    public void modifyObj(DstIncomeOrder t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstIncomeOrderMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstIncomeOrder", key="'DstIncomeOrderService_' + #root.methodName + '_' +#id")
    @Override
    public DstIncomeOrder queryObjById(int id) {
        return this.dstIncomeOrderMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstIncomeOrder", key="'DstIncomeOrderService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstIncomeOrder> queryAllObjByExample(DstIncomeOrderExample example) {
        return this.dstIncomeOrderMapper.selectByExample(example);
    }

    @Cacheable(value="dstIncomeOrder", key="'DstIncomeOrderService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstIncomeOrder> queryObjByPage(DstIncomeOrderExample example) {
        PageView<DstIncomeOrder> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstIncomeOrderMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Transactional
    @CacheEvict(value={"dstIncomeOrder"}, allEntries=true)
	@Override
	public DstIncomeOrder addIncome(Order order, List<DstIncomeOrderProduct> incomeOrderProducts, Member member) {
		int incomeTotal = 0;
		int pointTotal = 0;
		int levelId = 0;
		String levelName = null;
		for(DstIncomeOrderProduct dstIncomeOrderProduct: incomeOrderProducts){
			levelId = dstIncomeOrderProduct.getLevelId();
			levelName = dstIncomeOrderProduct.getLevelName();
			incomeTotal = incomeTotal + dstIncomeOrderProduct.getIncomeTotal();
			pointTotal = pointTotal + dstIncomeOrderProduct.getPointTotal();
		}
		
		DstIncomeOrder dstIncomeOrder = new DstIncomeOrder();
		dstIncomeOrder.setMemberId(member.getId());
		dstIncomeOrder.setMemberName(member.getName());
		dstIncomeOrder.setLevelId(levelId);
		dstIncomeOrder.setLevelName(levelName);
		dstIncomeOrder.setOrderId(order.getId());
		dstIncomeOrder.setOrderNo(order.getOrderNo());
		dstIncomeOrder.setOrderMemberId(order.getMemberId());
		dstIncomeOrder.setOrderMemberName(order.getMemberNickName());
		dstIncomeOrder.setOrderAmountTotal(order.getAmountTotal());
		dstIncomeOrder.setOrderAmountPay(order.getAmountPay());
		dstIncomeOrder.setIncomeAmountTotal(incomeTotal);
		dstIncomeOrder.setPointAmountTotal(pointTotal);
		dstIncomeOrder.setIncomeType(DstIncomeOrder.TYPE_INCOME);
		this.addObj(dstIncomeOrder);
		
		for(DstIncomeOrderProduct dstIncomeOrderProduct : incomeOrderProducts){
			dstIncomeOrderProduct.setIncomeOrderId(dstIncomeOrder.getId());
			this.dstIncomeOrderProductService.addObj(dstIncomeOrderProduct);
		}
		
		this.dstIncomeMemberService.addIncome(incomeTotal, pointTotal, member);
		return dstIncomeOrder;
	}

    @Transactional
	@Override
	public void refundIncome(int orderId) {
		DstIncomeOrderExample exRefund = new DstIncomeOrderExample();
		exRefund.createCriteria().andOrderIdEqualTo(orderId).andIncomeTypeEqualTo(DstIncomeOrder.TYPE_REFUND);
		List<DstIncomeOrder> list = this.queryAllObjByExample(exRefund);
		
		if (CollectionUtils.isNotEmpty(list)) {
			throw new CommonException("已经有退费记录了，无法在执行退费");
		}
		
		DstIncomeOrderExample exIncomeOrder = new DstIncomeOrderExample();
		exIncomeOrder.createCriteria().andOrderIdEqualTo(orderId).andIncomeTypeEqualTo(DstIncomeOrder.TYPE_INCOME);
		list = this.queryAllObjByExample(exIncomeOrder);
		
		for(DstIncomeOrder incomeOrder: list){
			int income = -incomeOrder.getIncomeAmountTotal();
			int point = -incomeOrder.getPointAmountTotal();
			
			incomeOrder.setId(null);
			incomeOrder.setCreateTime(null);
			incomeOrder.setIncomeType(DstIncomeOrder.TYPE_REFUND);
			incomeOrder.setIncomeAmountTotal(income);
			incomeOrder.setPointAmountTotal(point);
			
			this.addObj(incomeOrder);
			this.dstIncomeMemberService.addIncome(income, point, this.memberService.queryObjById(incomeOrder.getMemberId()));
		}
		
	}
}