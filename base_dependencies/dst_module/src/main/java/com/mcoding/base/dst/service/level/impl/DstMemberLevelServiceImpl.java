package com.mcoding.base.dst.service.level.impl;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.level.DstLevel;
import com.mcoding.base.dst.bean.level.DstMemberLevel;
import com.mcoding.base.dst.bean.level.DstMemberLevelExample;
import com.mcoding.base.dst.persistence.level.DstMemberLevelMapper;
import com.mcoding.base.dst.service.level.DstLevelService;
import com.mcoding.base.dst.service.level.DstMemberLevelService;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.member.MemberExample;
import com.mcoding.base.member.service.member.MemberService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("defaultDstMemberLevelService")
public class DstMemberLevelServiceImpl implements DstMemberLevelService {
    @Resource
    protected DstMemberLevelMapper dstMemberLevelMapper;
    
    @Resource
    protected DstLevelService dstLevelService;
    
    @Resource
    protected MemberService memberService;

    @CacheEvict(value={"dstMemberLevel","memberCache"}, allEntries=true)
    @Override
    public void addObj(DstMemberLevel t) {
        this.dstMemberLevelMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstMemberLevel","memberCache"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstMemberLevelMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstMemberLevel","memberCache"}, allEntries=true)
    @Override
    public void modifyObj(DstMemberLevel t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstMemberLevelMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstMemberLevel", key="'DstMemberLevelService_' + #root.methodName + '_' +#id")
    @Override
    public DstMemberLevel queryObjById(int id) {
        return this.dstMemberLevelMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstMemberLevel", key="'DstMemberLevelService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstMemberLevel> queryAllObjByExample(DstMemberLevelExample example) {
        return this.dstMemberLevelMapper.selectByExample(example);
    }

    @Cacheable(value="dstMemberLevel", key="'DstMemberLevelService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstMemberLevel> queryObjByPage(DstMemberLevelExample example) {
        PageView<DstMemberLevel> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstMemberLevelMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"dstMemberLevel","memberCache"}, allEntries=true)
	@Override
	public DstMemberLevel applyForDistributor(Member member, Member parentMember) {
    	if (member == null || parentMember == null) {
			throw new NullPointerException("会员或上级经销商，不能为空");
		}
		
		DstMemberLevel parentLevel = this.queryByMemberId(parentMember.getId());
		if (parentLevel == null || parentLevel.getLevelId() == null) {
			throw new CommonException("申请加入的上级经销商,没有级别资料");
		}
		
		DstLevel level = this.dstLevelService.queryChildLevelById(parentLevel.getLevelId());
		if (level == null) {
			throw new CommonException("申请加盟的上级经销商，没有下级可申请");
		}
		
		DstMemberLevel memberLevel = this.queryByMemberId(member.getId()); 
		if (memberLevel != null) {
			if (!parentMember.getId().equals(memberLevel.getParentMemberId())) {
				throw new CommonException("该会员已经是其他经销商的下线");
			}
			
			if (memberLevel.getLevelId() != null) {
				throw new CommonException("该会员已经是经销商，级别["+memberLevel.getLevelName()+"]");
				
			}
			
			DstMemberLevel tmp = new DstMemberLevel();
			tmp.setId(memberLevel.getId());
			tmp.setLevelId(level.getId());
			tmp.setLevelName(level.getLevelName());
			tmp.setApplyTime(new Date());
			this.modifyObj(tmp);
			
		}else{
			memberLevel = new DstMemberLevel();
			memberLevel.setLevelId(memberLevel.getId());
			memberLevel.setLevelName(level.getLevelName());
			memberLevel.setMemberId(member.getId());
			memberLevel.setMemberName(member.getName());
			memberLevel.setParentMemberId(parentMember.getId());
			memberLevel.setParentMemberName(parentMember.getName());
			memberLevel.setApplyTime(new Date());
			this.addObj(memberLevel);
		}
				
		return memberLevel;
	}

    @CacheEvict(value={"dstMemberLevel","memberCache"}, allEntries=true)
	@Override
	public DstMemberLevel applyForDistributor(Member member, DstLevel dstLevel) {
    	if (member == null || dstLevel == null) {
			throw new NullPointerException("会员或上级经销商级别，不能为空");
		}
		
    	DstMemberLevel memberLevel = this.queryByMemberId(member.getId());
    	if (memberLevel !=null) {
    		if ( memberLevel.getLevelId() != null) {
				throw new CommonException("该会员已经是经销商，级别["+memberLevel.getLevelName()+"]");
			}
    		
    		DstMemberLevel tmp = new DstMemberLevel();
    		tmp.setId(memberLevel.getId());
    		tmp.setLevelId(dstLevel.getId());
    		tmp.setLevelName(dstLevel.getLevelName());
    		tmp.setApplyTime(new Date());
    		this.modifyObj(tmp);
    		
		}else{
			memberLevel = new DstMemberLevel();
			memberLevel.setLevelId(dstLevel.getId());
			memberLevel.setLevelName(dstLevel.getLevelName());
			memberLevel.setMemberId(member.getId());
			memberLevel.setMemberName(member.getName());
			memberLevel.setApplyTime(new Date());
			this.addObj(memberLevel);
			
		}
    			
		return memberLevel;
	}

    @Cacheable(value="dstMemberLevel", key="'DstMemberLevelService_' + #root.methodName + '_'+ #memberId")
	@Override
	public Member queryParentDistributor(int memberId) {
    	DstMemberLevelExample example = new DstMemberLevelExample();
    	example.createCriteria()
    	       .andMemberIdEqualTo(memberId)
    	       .andIsEnableEqualTo(Constant.YES_INT);
    	
    	List<DstMemberLevel> list = this.queryAllObjByExample(example);
    	if (CollectionUtils.isEmpty(list)) {
			return null;
		}
    	
    	if (list.get(0).getParentMemberId() == null) {
			return null;
		}
    	
    	return this.memberService.queryObjById(list.get(0).getParentMemberId());
	}

    @Cacheable(value="dstMemberLevel", key="'DstMemberLevelService_' + #root.methodName + '_'+ #memberId")
	@Override
	public List<Member> queryChildrenDistributor(int memberId) {
    	DstMemberLevelExample example = new DstMemberLevelExample();
    	example.createCriteria()
    	       .andParentMemberIdEqualTo(memberId)
    	       .andIsEnableEqualTo(Constant.YES_INT);
    	
    	List<DstMemberLevel> list = this.queryAllObjByExample(example);
    	if (CollectionUtils.isEmpty(list)) {
			return null;
		}
    	
    	List<Integer> memberIdList = new ArrayList<>();
    	for(DstMemberLevel level : list){
    		memberIdList.add(level.getMemberId());
    	}
    	
    	MemberExample memberExample = new MemberExample();
    	memberExample.createCriteria().andIdIn(memberIdList);
    	
    	return this.memberService.queryAllObjByExample(memberExample);
	}

    @Cacheable(value="dstMemberLevel", key="'DstMemberLevelService_' + #root.methodName + '_'+ #memberId")
	@Override
	public DstMemberLevel queryByMemberId(int memberId) {
		DstMemberLevelExample example = new DstMemberLevelExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memberId)
		       .andIsEnableEqualTo(Constant.YES_INT);
		
		List<DstMemberLevel> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
//		return this.dstLevelService.queryObjById(list.get(0).getLevelId());
		return list.get(0);
	}

    @CacheEvict(value={"dstMemberLevel","memberCache"}, allEntries=true)
	@Override
	public DstMemberLevel recruitNewMember(Member member, Member parentMember) {
    	if (member == null || parentMember == null) {
			throw new NullPointerException("会员或上级经销商，不能为空");
		}
    	
    	DstMemberLevel parentLevel = this.queryByMemberId(parentMember.getId());
    	if (parentLevel == null || parentLevel.getLevelId() == null) {
    		throw new CommonException("招收下线的经销商，并没有级别资料");
		}
    	
    	DstMemberLevel memberLevel = this.queryByMemberId(member.getId());
    	if (memberLevel != null && memberLevel.getParentMemberId() != null && !parentMember.getId().equals(memberLevel.getParentMemberId())) {
			throw new CommonException("招收的下线,已经是其他人的下线");
		}
    	
    	if (memberLevel != null && memberLevel.getParentMemberId().equals(parentMember.getId())) {
			return memberLevel;
		}
    	
    	memberLevel = new DstMemberLevel();
		memberLevel.setMemberId(member.getId());
		memberLevel.setMemberName(member.getName());
		memberLevel.setParentMemberId(parentMember.getId());
		memberLevel.setParentMemberName(parentMember.getName());
		this.addObj(memberLevel);
		
		return memberLevel;
	}
}