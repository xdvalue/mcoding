package com.mcoding.base.dst.service.income.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.income.DstIncomeMember;
import com.mcoding.base.dst.bean.income.DstIncomeMemberExample;
import com.mcoding.base.dst.persistence.income.DstIncomeMemberMapper;
import com.mcoding.base.dst.service.income.DstIncomeMemberService;
import com.mcoding.base.member.bean.member.Member;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("defaultDstIncomeMemberService")
public class DstIncomeMemberServiceImpl implements DstIncomeMemberService {
    @Resource
    protected DstIncomeMemberMapper dstIncomeMemberMapper;

    @CacheEvict(value={"dstIncomeMember"}, allEntries=true)
    @Override
    public void addObj(DstIncomeMember t) {
        this.dstIncomeMemberMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstIncomeMember"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstIncomeMemberMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstIncomeMember"}, allEntries=true)
    @Override
    public void modifyObj(DstIncomeMember t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstIncomeMemberMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstIncomeMember", key="'DstIncomeMemberService_' + #root.methodName + '_' +#id")
    @Override
    public DstIncomeMember queryObjById(int id) {
        return this.dstIncomeMemberMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstIncomeMember", key="'DstIncomeMemberService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstIncomeMember> queryAllObjByExample(DstIncomeMemberExample example) {
        return this.dstIncomeMemberMapper.selectByExample(example);
    }

    @Cacheable(value="dstIncomeMember", key="'DstIncomeMemberService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstIncomeMember> queryObjByPage(DstIncomeMemberExample example) {
        PageView<DstIncomeMember> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstIncomeMemberMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Transactional
	@Override
	public DstIncomeMember addIncome(int incomeTotal, int pointTotal, Member member) {
    	if (member == null) {
			throw new NullPointerException("经销商资料为空，无法添加佣金");
		}
    	
		DstIncomeMemberExample example = new DstIncomeMemberExample();
		example.createCriteria().andMemberIdEqualTo(member.getId());
		
		DstIncomeMember dstIncomeMember = null; 
		List<DstIncomeMember> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			dstIncomeMember = new DstIncomeMember();
			dstIncomeMember.setMemberId(member.getId());
			dstIncomeMember.setMemberName(member.getName());
			dstIncomeMember.setIncomeTotal(0);
			dstIncomeMember.setPointTotal(0);
			this.addObj(dstIncomeMember);
		
		}else{
			dstIncomeMember = list.get(0);
		}
		
		if (incomeTotal == 0 && pointTotal == 0) {
			return dstIncomeMember;
		}
		
		dstIncomeMember.setIncomeTotal(dstIncomeMember.getIncomeTotal() + incomeTotal);
		dstIncomeMember.setPointTotal(dstIncomeMember.getPointTotal() + pointTotal);
		
		this.modifyObj(dstIncomeMember);
		return dstIncomeMember;
	}
}