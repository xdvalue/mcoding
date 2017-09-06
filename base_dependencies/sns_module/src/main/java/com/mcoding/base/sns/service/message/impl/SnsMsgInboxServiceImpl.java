package com.mcoding.base.sns.service.message.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.message.CommentMsgInbox;
import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgExample;
import com.mcoding.base.sns.bean.message.SnsMsgExample.Criteria;
import com.mcoding.base.sns.bean.message.SnsMsgInbox;
import com.mcoding.base.sns.bean.message.SnsMsgInboxExample;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.persistence.message.SnsMsgInboxMapper;
import com.mcoding.base.sns.service.comment.SnsCommentService;
import com.mcoding.base.sns.service.message.SnsMsgInboxService;
import com.mcoding.base.sns.service.message.SnsMsgService;
import com.mcoding.base.sns.service.post.SnsPostService;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Service("snsMsgInboxService")
public class SnsMsgInboxServiceImpl implements SnsMsgInboxService {
	@Resource
	protected SnsMsgInboxMapper snsMsgInboxMapper;

	@Resource
	protected SnsCommentService snsCommentService;

	@Resource
	protected SnsPostService snsPostService;

	@Resource
	protected SnsMsgService snsMsgService;

	@CacheEvict(value = { "snsMsgInbox" }, allEntries = true)
	@Override
	public void addObj(SnsMsgInbox t) {
		this.snsMsgInboxMapper.insertSelective(t);
	}

	@CacheEvict(value = { "snsMsgInbox" }, allEntries = true)
	@Override
	public void deleteObjById(int id) {
		this.snsMsgInboxMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value = { "snsMsgInbox" }, allEntries = true)
	@Override
	public void modifyObj(SnsMsgInbox t) {
		if (t.getId() == null || t.getId() == 0) {
			throw new NullPointerException("id 为空，无法更新");
		}
		this.snsMsgInboxMapper.updateByPrimaryKeySelective(t);
	}

	@Cacheable(value = "snsMsgInbox", key = "'SnsMsgInboxService_' + #root.methodName + '_' +#id")
	@Override
	public SnsMsgInbox queryObjById(int id) {
		return this.snsMsgInboxMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value = "snsMsgInbox", key = "'SnsMsgInboxService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<SnsMsgInbox> queryAllObjByExample(SnsMsgInboxExample example) {
		return this.snsMsgInboxMapper.selectByExample(example);
	}

	@Cacheable(value = "snsMsgInbox", key = "'SnsMsgInboxService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<SnsMsgInbox> queryObjByPage(SnsMsgInboxExample example) {
		PageView<SnsMsgInbox> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.snsMsgInboxMapper.selectByExampleByPage(example));
		return pageView;
	}

	@Override
	public CommentMsgInbox transform(SnsMsgInbox msgInbox) {
		if (msgInbox == null) {
			return null;
		}

		SnsMsg msg = msgInbox.getSnsMsg();
		SnsComment comment = this.snsCommentService.queryObjById(msg.getRefId());
		SnsPost post = this.snsPostService.queryObjById(comment.getPostId());

		CommentMsgInbox commentMsg = new CommentMsgInbox();
		commentMsg.setSnsMsg(msgInbox.getSnsMsg());
		commentMsg.setSnsComment(comment);
		commentMsg.setSnsPost(post);

		commentMsg.setId(msgInbox.getId());
		commentMsg.setStoreId(msgInbox.getStoreId());
		commentMsg.setMsgId(msgInbox.getMsgId());
		commentMsg.setMsgType(msgInbox.getMsgType());
		commentMsg.setReceiverId(msgInbox.getReceiverId());
		commentMsg.setIsRead(msgInbox.getIsRead());
		commentMsg.setReadTime(msgInbox.getReadTime());
		commentMsg.setCreateTime(msgInbox.getCreateTime());
		return commentMsg;
	}

	@Override
	public List<CommentMsgInbox> transform(List<SnsMsgInbox> msgInboxList) {
		if (CollectionUtils.isEmpty(msgInboxList)) {
			return null;
		}

		List<CommentMsgInbox> result = new ArrayList<>(msgInboxList.size());
		for (int i = 0; i < msgInboxList.size(); i++) {
			SnsMsgInbox tmp = msgInboxList.get(i);
			CommentMsgInbox commentMsg = this.transform(tmp);

			if (commentMsg != null) {
				result.add(commentMsg);
			}
		}

		return result;
	}

	@Override
	public int countMsg(int memberId, int storeId, int msgTypeComment) {
		int count = 0;

		if (SnsMsg.MSG_TYPE_SYSTEM.equals(msgTypeComment)) {
			this.addUnReadedSystemMsg(memberId, storeId);
		}
		
		SnsMsgInboxExample ex = new SnsMsgInboxExample();
		ex.createCriteria().andReceiverIdEqualTo(memberId).andStoreIdEqualTo(storeId).andMsgTypeEqualTo(msgTypeComment)
				.andIsReadEqualTo(Constant.NO_INT);

		count = this.snsMsgInboxMapper.countByExample(ex);
		return count;
	}
	
	/**
	 * 给用户添加未读的系统消息
	 * @param memberId
	 * @param storeId
	 */
	private void addUnReadedSystemMsg(int memberId, int storeId){
		//1、统计用户信箱中的已有系统消息
		SnsMsgInboxExample snsMsgInboxExample = new SnsMsgInboxExample();
		snsMsgInboxExample.createCriteria()
		                  .andStoreIdEqualTo(storeId)
		                  .andReceiverIdEqualTo(memberId)
		                  .andMsgTypeEqualTo(SnsMsg.MSG_TYPE_SYSTEM);
		
		SnsMsgInboxService service = SpringContextHolder.getBean("snsMsgInboxService");
		List<SnsMsgInbox> msgInboxList = service.queryAllObjByExample(snsMsgInboxExample);
		
		List<Integer> ids = new ArrayList<>(msgInboxList.size());
		
		for (int i = 0; i < msgInboxList.size(); i++) {
			ids.add(msgInboxList.get(i).getMsgId());
		}
		
		//2、找出用户信箱中，未有的系统消息
		Date now = new Date();
		SnsMsgExample snsMsgExample = new SnsMsgExample();
		Criteria cri1 = snsMsgExample.createCriteria();
		cri1.andReceiverIdIsNotNull();
		cri1.andIsEnableEqualTo(Constant.YES_INT);
		cri1.andTypeEqualTo(SnsMsg.MSG_TYPE_SYSTEM);
		cri1.andEnableTimeLessThan(now);
		
		Criteria cri2 = snsMsgExample.or();
		cri2.andReceiverIdIsNull();
		cri2.andIsEnableEqualTo(Constant.YES_INT);
		cri2.andTypeEqualTo(SnsMsg.MSG_TYPE_SYSTEM);
		cri2.andEnableTimeIsNull();
		
		if (CollectionUtils.isNotEmpty(ids)) {
			cri1.andIdNotIn(ids);
			cri2.andIdNotIn(ids);
		}

		List<SnsMsg> list = this.snsMsgService.queryAllObjByExample(snsMsgExample);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}
		
		//把未有的系统消息，放入信箱
		for (int i = 0; i < list.size(); i++) {
			SnsMsgInbox snsMsgInbox = new SnsMsgInbox();
			snsMsgInbox.setStoreId(list.get(i).getStoreId());
			snsMsgInbox.setMsgId(list.get(i).getId());
			snsMsgInbox.setMsgType(SnsMsg.MSG_TYPE_SYSTEM);
			snsMsgInbox.setReceiverId(memberId);
			snsMsgInbox.setIsRead(Constant.NO_INT);
			snsMsgInbox.setCreateTime(list.get(i).getCreateTime());
//			this.se.insertSelective(snsMsgInbox);
			service.addObj(snsMsgInbox);
		}
	}
}