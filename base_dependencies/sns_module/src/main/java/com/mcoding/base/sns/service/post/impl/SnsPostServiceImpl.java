package com.mcoding.base.sns.service.post.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.point.service.MemberPointable;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.comment.SnsCommentExample;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.bean.post.SnsPostExample;
import com.mcoding.base.sns.bean.post.SnsPostImg;
import com.mcoding.base.sns.bean.post.SnsPostImgExample;
import com.mcoding.base.sns.persistence.favorite.SnsFavoriteMapper;
import com.mcoding.base.sns.persistence.post.SnsPostImgMapper;
import com.mcoding.base.sns.persistence.post.SnsPostMapper;
import com.mcoding.base.sns.service.comment.SnsCommentService;
import com.mcoding.base.sns.service.message.command.MvPostMsgCommand;
import com.mcoding.base.sns.service.message.command.TopOrEssencePostMsgCommand;
import com.mcoding.base.sns.service.message.event.SendMsgEvent;
import com.mcoding.base.sns.service.module.SnsModuleService;
import com.mcoding.base.sns.service.post.SnsPostExtInfoService;
import com.mcoding.base.sns.service.post.SnsPostInterceptor;
import com.mcoding.base.sns.service.post.SnsPostService;
import com.mcoding.base.sns.service.rule.point.EssencePostPointRuleHandler;
import com.mcoding.base.sns.service.rule.point.PostPointRuleHandler;
import com.mcoding.base.ui.utils.StoreUtils;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Service("snsPostService")
public class SnsPostServiceImpl implements SnsPostService {
	
	@Resource
	protected SnsPostExtInfoService snsPostExtInfoService;

	@Resource
	protected SnsPostMapper snsPostMapper;

	@Resource
	protected SnsPostImgMapper snsPostImgMapper;

	@Resource
	protected SnsFavoriteMapper snsFavoriteMapper;
	
	@Resource
	protected SnsCommentService snsCommentService;
	
	@Autowired
	protected SnsModuleService snsModuleService;
	
	@Autowired
	protected ApplicationContext applicationContext;
	
	@CacheEvict(value={"snsPost"}, allEntries=true)
	@MemberPointable(using=PostPointRuleHandler.class, targetMemberIndex=0)
	@Override
	@Transactional
	public void addObj(SnsPost t) {
		if(t.getModuleId() == null || t.getModuleId() <=0) t.setModuleId(1);
		
        if (StringUtils.isBlank(t.getModuleName())) {
			t.setModuleName(this.snsModuleService.queryObjById(t.getModuleId()).getName());
		}
		
		snsPostMapper.insertSelective(t);
		this.snsPostExtInfoService.addObj(t.getId());
		
		this.addOrUpdateImg(t.getId(), t.getPostImgs());
		this.snsModuleService.addPostCountNum(t.getModuleId(), 1);
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Override
	public void deleteObjById(int id) {
		this.snsPostMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Transactional
	@Override
	public void modifyObj(SnsPost t) {
		if(t.getModuleId() == null || t.getModuleId() <=0) t.setModuleId(1);
		if (StringUtils.isBlank(t.getModuleName())) {
			t.setModuleName(this.snsModuleService.queryObjById(t.getModuleId()).getName());
		}
		
		if (t.getId() == null || t.getId() <= 0) {
			throw new NullPointerException("id为空，不能更新");
		}
		SnsPost tmp = this.queryObjById(t.getId());
		
		this.snsPostMapper.updateByPrimaryKeySelective(t);
		this.addOrUpdateImg(t.getId(), t.getPostImgs());
		
		if (!tmp.getModuleId().equals(t.getModuleId())) {
			//如果更改模块，则更改模块的帖子总数
			this.snsModuleService.addPostCountNum(t.getModuleId(), 1);
		}
	}
	
	private void addOrUpdateImg(int postId, List<SnsPostImg> postImgs){
		if (CollectionUtils.isEmpty(postImgs)) {
			return;
		}

		SnsPostImgExample example = new SnsPostImgExample();
		example.createCriteria().andPostIdEqualTo(postId);
		
		this.snsPostImgMapper.deleteByExample(example);
		for (SnsPostImg postImg : postImgs) {

			String imgUrl = postImg.getImgUrl();
			if (StringUtils.isBlank(imgUrl)) {
				continue;
			}

			postImg.setPostId(postId);
			snsPostImgMapper.insertSelective(postImg);
		}

	}

	@Cacheable(value="snsPost", key="'SnsPostService_' + #root.methodName + '_' +#id")
	@Override
	public SnsPost queryObjById(int id) {
		return snsPostMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="snsPost", key="'SnsPostService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public List<SnsPost> queryAllObjByExample(SnsPostExample example) {
		return this.snsPostMapper.selectByExampleWithBLOBs(example);
	}

	@Cacheable(value="snsPost", key="'SnsPostService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public PageView<SnsPost> queryObjByPage(SnsPostExample example) {
		PageView<SnsPost> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(snsPostMapper.selectByExampleByPage(example));
		return pageView;
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Override
	@Transactional
	public void addOrRemoveLike(int storeId, int memberId, int postId, boolean isLike) {
		this.snsPostExtInfoService.addOrRemoveLike(storeId, memberId, postId, isLike);
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Override
	public void addOrRemoveDislike(int storeId, int memberId, int postId, boolean isDisLike) {
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Transactional
	@Override
	public void post(SnsPost snsPost, Member member) {
		Map<String, SnsPostInterceptor> map = SpringContextHolder.getBeans(SnsPostInterceptor.class);
		if (MapUtils.isEmpty(map)) {
			this.addObj(snsPost);
			return;
		}

		List<SnsPostInterceptor> list = this.getListFormMap(map);
		for (int i = 0; CollectionUtils.isNotEmpty(list) && i < list.size(); i++) {
			if (!list.get(i).preHandle(snsPost, member)) {
				return;
			}
		}

//		this.addObj(snsPost);
		SnsPostService proxySelf = SpringContextHolder.getBean("snsPostService");
		proxySelf.addObj(snsPost);

		for (int i = list.size() - 1; CollectionUtils.isNotEmpty(list) && i >= 0; i--) {
			list.get(i).afterCompletion(snsPost, member);
		}

	}

	private List<SnsPostInterceptor> getListFormMap(Map<String, SnsPostInterceptor> map) {
		List<SnsPostInterceptor> list = new ArrayList<>();
		Set<Entry<String, SnsPostInterceptor>> entrySet = map.entrySet();
		Iterator<Entry<String, SnsPostInterceptor>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next().getValue());
		}
		return list;
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Override
	public void upOrDownTop(int storeId, int postId, boolean isUp) {
		if (true == isUp) {
			this.snsPostMapper.updateOrderNumAsMax(postId, storeId);
			
			SnsPostService proxySelf = SpringContextHolder.getBean("snsPostService");
			SnsPost snsPost = proxySelf.queryObjById(postId);
			
			TopOrEssencePostMsgCommand command = new TopOrEssencePostMsgCommand(snsPost, TopOrEssencePostMsgCommand.ACTION_UP_POST);
			this.applicationContext.publishEvent(new SendMsgEvent(command));
			
		} else {
			SnsPost post = new SnsPost();
			post.setId(postId);
			post.setOrderNum(0);
			snsPostMapper.updateByPrimaryKeySelective(post);
		}
	}
	
	@CacheEvict(value={"snsPost"}, allEntries=true)
	public void upOrDownTopInHome(int storeId, int postId, boolean isUp){
		if (true == isUp) {
			this.snsPostMapper.updateOrderNumInHomeAsMax(postId, storeId);
			
			SnsPostService proxySelf = SpringContextHolder.getBean("snsPostService");
			SnsPost snsPost = proxySelf.queryObjById(postId);
			
			TopOrEssencePostMsgCommand command = new TopOrEssencePostMsgCommand(snsPost, TopOrEssencePostMsgCommand.ACTION_UP_POST_IN_HOME);
			this.applicationContext.publishEvent(new SendMsgEvent(command));
		} else {
			SnsPost post = new SnsPost();
			post.setId(postId);
			post.setOrderInHome(0);
			snsPostMapper.updateByPrimaryKeySelective(post);
		}
	}

	@Cacheable(value="snsPost", key="'SnsPostService_' + #memberId + '_'+ #storeId + '_' + #pageNo +'_' + #pageSize")
	@Override
	public PageView<SnsPost> queryByMemberComment(int memberId, int storeId, int pageNo, int pageSize) {
		SnsCommentExample commentExample = new SnsCommentExample();
		commentExample.createCriteria().andStoreIdEqualTo(storeId)
				.andMemberIdEqualTo(memberId);
//		List<SnsComment> cts = commentMapper.selectByExample(commentExample);
		List<SnsComment> cts = this.snsCommentService.queryAllObjByExample(commentExample);

		PageView<SnsPost> pageView = new PageView<>(pageNo, pageSize);

		if (CollectionUtils.isNotEmpty(cts)) {
			Set<Integer> postIdSet = new HashSet<>();
			for (SnsComment ct : cts) {
				postIdSet.add(ct.getPostId());
			}

			SnsPostExample postExample = new SnsPostExample();
			postExample.createCriteria().andIdIn(new ArrayList<>(postIdSet));

			postExample.setPageView(pageView);
			postExample.setOrderByClause("id DESC");

			pageView.setQueryResult(snsPostMapper.selectByExampleByPage(postExample));
		}

		return pageView;
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Override
	public void modifyForComment(int postId) {
//		this.snsPostMapper.addOrRemoveComment(postId, true);
		this.snsPostExtInfoService.addCommentNum(postId);
		
		this.snsPostMapper.updateReplyTimeByPostId(postId);
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Override
	public void checkPost(Integer id, int isCheck) {
		SnsPost tmp = new SnsPost();
		tmp.setId(id);
		tmp.setIsCheck(isCheck);
		this.modifyObj(tmp);
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@MemberPointable(using=EssencePostPointRuleHandler.class, targetMemberIndex=0, pointSourceIndex=1)
	@Override
	public void setEssencePost(Integer id, int isEssence) {
		SnsPost snsPost = new SnsPost();
		snsPost.setId(id);
		if(Constant.YES_INT.equals(isEssence)){
			snsPost.setTypeFlag(SnsPost.TYPE_FLAG_BOUTIQUE);
		}else{
			snsPost.setTypeFlag(SnsPost.TYPE_FLAG_COMMON);
		}
		this.modifyObj(snsPost);
		
		if (Constant.YES_INT.equals(isEssence)) {
			SnsPostService proxySelf = SpringContextHolder.getBean("snsPostService");
			SnsPost tmp = proxySelf.queryObjById(id);
			
			TopOrEssencePostMsgCommand command = new TopOrEssencePostMsgCommand(tmp, TopOrEssencePostMsgCommand.ACTION_ESSENCE);
			this.applicationContext.publishEvent(new SendMsgEvent(command));
		}
	}

	@CacheEvict(value={"snsPost"}, allEntries=true)
	@Transactional
	@Override
	public void mvToModule(List<Integer> idList, SnsModule snsModule) {
		
		for(int i=0; i<idList.size(); i++){
			SnsPost snsPost = this.queryObjById(idList.get(i));
			if (snsPost == null) {
				throw new NullPointerException("没有找到需要移动的帖子,id:" + idList.get(i));
			}
			
			SnsPost tmp = new SnsPost();
			tmp.setId(idList.get(i));
			tmp.setModuleId(snsModule.getId());
			tmp.setModuleName(snsModule.getName());
			this.modifyObj(tmp);
			
			this.snsModuleService.addPostCountNum(snsPost.getModuleId(), -1);
			
			SnsModule preSnsModule = this.snsModuleService.queryObjById(snsPost.getModuleId());
			MvPostMsgCommand command = new MvPostMsgCommand(snsPost, preSnsModule, snsModule, StoreUtils.getStoreFromThreadLocal());
			this.applicationContext.publishEvent(new SendMsgEvent(command));
		}
		
		
	}

}
