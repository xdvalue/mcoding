package com.mcoding.comp.wechat.member.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.member.bean.WxMember;
import com.mcoding.comp.wechat.member.bean.WxMemberExample;
import com.mcoding.comp.wechat.member.persistence.WxMemberMapper;
import com.mcoding.comp.wechat.member.service.WxMemberService;
import com.mcoding.comp.wechat.member.utils.WxMemberUtils;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service("wxMemberService")
public class WxMemberServiceImpl implements WxMemberService {
	
	private static Logger logger = LoggerFactory.getLogger(WxMemberServiceImpl.class);
	
	@Autowired
	private WxMemberMapper wxMemberMapper;

	@CacheEvict(value="wxMember", allEntries=true)
	@Override
	public void addObj(WxMember t) {
		this.wxMemberMapper.insertSelective(t);
	}

	@CacheEvict(value="wxMember", allEntries=true)
	@Override
	public void deleteObjById(int id) {
		this.wxMemberMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value="wxMember", allEntries=true)
	@Override
	public void modifyObj(WxMember t) {
		if (t.getId() == null) {
			throw new NullPointerException("id为空，无法更新");
		}
		this.wxMemberMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value="wxMember", key="'WxMember_' +#root.methodName + '_' + #id")
	@Override
	public WxMember queryObjById(int id) {
		return this.wxMemberMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="wxMember", key="'WxMember_' +#root.methodName + '_' + #example.toJson()")
	@Override
	public List<WxMember> queryAllObjByExample(WxMemberExample example) {
		return this.wxMemberMapper.selectByExample(example);
	}

	@Cacheable(value="wxMember", key="'WxMember_' +#root.methodName + '_' + #example.toJson()")
	@Override
	public PageView<WxMember> queryObjByPage(WxMemberExample example) {
		PageView<WxMember> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
		}
		
		pageView.setQueryResult(this.wxMemberMapper.selectByExampleByPage(example));
		return pageView;
	}
	
	@CacheEvict(value="wxMember", allEntries=true)
//	@Override
	public WxMember createOrEditWxMember(WxMpUser wxMpUser) {
		return this.createOrEditWxMember(wxMpUser, null, null);
	}

	@CacheEvict(value="wxMember", allEntries=true)
	@Override
	public WxMember createOrEditWxMember(WxMpUser wxMpUser, String subscribeKey, String originId) {
		WxMember wxMember = WxMemberUtils.getWxMemberFormMpUser(wxMpUser);
		wxMember.setWxAccountOriginId(originId);
		
		String openid = wxMpUser.getOpenId();
		WxMemberExample wxMemberExample = new WxMemberExample();
		wxMemberExample.createCriteria().andWxOpenidEqualTo(openid);
		
		List<WxMember> list = this.queryAllObjByExample(wxMemberExample);
		
		if(CollectionUtils.isEmpty(list)){
			if (Constant.YES_INT.equals(wxMember.getWxSubscribe())) {
				wxMember.setWxFirstSubscribeTime(new Date());
			}
			wxMember.setWxSubscribeKey(subscribeKey);
			this.addObj(wxMember);
		}else{
			
			if (wxMember.getWxFirstSubscribeTime() == null && Constant.YES_INT.equals(wxMember.getWxSubscribe())) {
				wxMember.setWxFirstSubscribeTime(new Date());
				wxMember.setWxSubscribeKey(subscribeKey);
			}
			
			wxMember.setId(list.get(0).getId());
			this.modifyObj(wxMember);
			wxMember = this.queryObjById(list.get(0).getId());
		}
		return wxMember;
	}

	@Override
	public void updateForUnsubscribe(String openid) {
		WxMemberExample example = new WxMemberExample();
		example.createCriteria().andWxOpenidEqualTo(openid);
		
		WxMember wxMember = new WxMember();
		wxMember.setWxSubscribe(Constant.NO_INT);
		
		this.wxMemberMapper.updateByExampleSelective(wxMember, example);
	}

	@Cacheable(value="wxMember", key="'WxMember_' +#root.methodName + '_' + #openid")
	@Override
	public WxMember queryByOpenId(String openid) {
		WxMemberExample wxMemberExample = new WxMemberExample();
		wxMemberExample.createCriteria().andWxOpenidEqualTo(openid);
		
		List<WxMember> list = this.wxMemberMapper.selectByExample(wxMemberExample);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		if (list.size()>1) {
			logger.warn("please pay attention on wxmember["+openid+"], there are two or more same datas in database.");
		}
		return list.get(0);
	}
	
}
