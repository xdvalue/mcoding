package com.mcoding.base.member.service.member.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.member.MemberFollower;
import com.mcoding.base.member.bean.member.MemberFollowerExample;
import com.mcoding.base.member.persistence.member.MemberFollowerMapper;
import com.mcoding.base.member.service.member.MemberFollowerService;
import com.mcoding.base.member.service.member.MemberService;

@Service("memberFollowerService")
public class MemberFollowerServiceImpl implements MemberFollowerService {
    
	@Resource
    protected MemberFollowerMapper memberFollowerMapper;
	
	@Resource
	protected MemberService memberService;

	@CacheEvict(value={"MemberFollowerService_parent", "MemberFollowerService_follows"}, key="#memberId")
	@Override
	public void follow(Member parent, Member children) {
		
		if (this.isFollowed(parent.getId(), children.getId())) {
			return;
		}
		
		MemberFollower follow = new MemberFollower();
		follow.setParentId(parent.getId());
		follow.setParentName(parent.getName());
		follow.setParentHeadImgUrl(parent.getHeadImgUrl());
		follow.setFollowerId(children.getId());
		follow.setFollowerName(children.getName());
		follow.setFollowerHeadImgUrl(children.getHeadImgUrl());
		
		this.memberFollowerMapper.insertSelective(follow);
	}

	@CacheEvict(value={"MemberFollowerService_parent", "MemberFollowerService_follows"}, key="#memberId")
	@Override
	public void unFollow(Member parent, Member children) {
		MemberFollowerExample example = new MemberFollowerExample();
		example.createCriteria()
		       .andParentIdEqualTo(parent.getId())
		       .andFollowerIdEqualTo(children.getId());
		
		this.memberFollowerMapper.deleteByExample(example);
		
	}

	@Cacheable(value="MemberFollowerService_follows", key="#memberId")
	@Override
	public List<Member> getFollowers(int memberId) {
		MemberFollowerExample example = new MemberFollowerExample();
		example.createCriteria().andParentIdEqualTo(memberId);
		
		List<MemberFollower> followers = this.memberFollowerMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(followers)) {
			return null;
		}
		
		List<Member> members = new ArrayList<>(followers.size());
		
		for(int i=0; i<followers.size(); i++){
			members.add(this.memberService.queryObjById(followers.get(i).getFollowerId()));
		}
		
		return members;
	}

	@Cacheable(value="MemberFollowerService_parent", key="#memberId")
	@Override
	public List<Member> getFollowedParent(int memberId) {
		MemberFollowerExample example = new MemberFollowerExample();
		example.createCriteria().andFollowerIdEqualTo(memberId);
		
		List<MemberFollower> followedParents = this.memberFollowerMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(followedParents)) {
			return null;
		}
		
        List<Member> members = new ArrayList<>(followedParents.size());
		
		for(int i=0; i<followedParents.size(); i++){
			members.add(this.memberService.queryObjById(followedParents.get(i).getParentId()));
		}
		
		return members;
	}

	@Override
	public boolean isFollowed(int parentId, int followerId) {
		MemberFollowerExample example = new MemberFollowerExample();
		example.createCriteria()
		       .andParentIdEqualTo(parentId)
		       .andFollowerIdEqualTo(followerId);
		int count = this.memberFollowerMapper.countByExample(example);
		
		return count > 0;
	}

    
}