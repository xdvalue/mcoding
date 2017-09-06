package com.mcoding.base.member.service.member.event;

import org.springframework.context.ApplicationEvent;

import com.mcoding.base.member.bean.member.Member;

public class FinishAddMemberEvent extends ApplicationEvent{
	
	private Integer storeId;
	
	public FinishAddMemberEvent(Member member, Integer addSource) {
		super(member);
	}

	public FinishAddMemberEvent(Member member) {
		super(member);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6373650984619597646L;

}
