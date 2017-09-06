package com.mcoding.base.sns.service.comment.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.point.service.MemberPointable;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.comment.SnsCommentExample;
import com.mcoding.base.sns.bean.comment.SnsCommentExample.Criteria;
import com.mcoding.base.sns.bean.comment.SnsCommentImg;
import com.mcoding.base.sns.bean.favorite.SnsFavorite;
import com.mcoding.base.sns.bean.favorite.SnsFavoriteExample;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.persistence.comment.SnsCommentImgMapper;
import com.mcoding.base.sns.persistence.comment.SnsCommentMapper;
import com.mcoding.base.sns.persistence.favorite.SnsFavoriteMapper;
import com.mcoding.base.sns.service.comment.SnsCommentInterceptor;
import com.mcoding.base.sns.service.comment.SnsCommentService;
import com.mcoding.base.sns.service.post.SnsPostService;
import com.mcoding.base.sns.service.rule.point.CommentPointRuleHandler;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Service("snsCommentService")
public class SnsCommentServiceImpl implements SnsCommentService {

	@Resource
	private SnsCommentMapper snsCommentMapper;

	@Resource
	private SnsCommentImgMapper snsCommentImgMapper;
	
	@Resource
	private SnsPostService snsPostService;

	@Resource
	private SnsFavoriteMapper snsFavoriteMapper;

	@CacheEvict(value="snsComment", allEntries=true)
	@MemberPointable(using=CommentPointRuleHandler.class, targetMemberIndex=0)
	@Override
	@Transactional
	public void addObj(SnsComment t) {
		snsCommentMapper.insertSelective(t);
		
		for(int i=0; CollectionUtils.isNotEmpty(t.getPostImgs()) && i<t.getPostImgs().size(); i++){
			
			SnsCommentImg img = t.getPostImgs().get(i);
			if (StringUtils.isBlank(img.getImgUrl())) {
				continue;
			}
			
			img.setCommentId(t.getId());
			this.snsCommentImgMapper.insertSelective(img);
		}

		Integer commentId = t.getParentCommentId();
		if (commentId != null && commentId > 0) {
			snsCommentMapper.addOrRemoveComment(commentId, true);
		}
		
		this.snsPostService.modifyForComment(t.getPostId());
	}
	
	@Cacheable(value="snsComment", key="'SnsCommentServiceImpl_list_' + #postId")
	@Override
	public PageView<SnsComment> queryAllObjByPostId(int postId) {
		SnsCommentExample example = new SnsCommentExample();
		Criteria criteria = example.createCriteria();
		criteria.andPostIdEqualTo(postId);
		criteria.andParentCommentIdEqualTo(Constant.NO_INT);
		
		example.setOrderByClause("create_time ASC");
		
		List<SnsComment> list = this.snsCommentMapper.selectByExample(example);
		PageView<SnsComment> pageView = new PageView<>(1, 5);
		pageView.setRowCount(list.size());
		pageView.setPageSize(1);
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public void deleteObjById(int id) {
		this.snsCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void modifyObj(SnsComment t) {
		this.snsCommentMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public SnsComment queryObjById(int id) {
		return this.snsCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SnsComment> queryAllObjByExample(SnsCommentExample example) {
		return this.snsCommentMapper.selectByExample(example);
	}

	@Override
	public PageView<SnsComment> queryObjByPage(SnsCommentExample example) {
		PageView<SnsComment> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		List<SnsComment> comments = snsCommentMapper.selectByExampleByPage(example);
		pageView.setQueryResult(comments);

		return pageView;
	}

	@Override
	public Map<String, Object> addOrRemoveLike(int storeId, int memberId, int commentId, boolean isLike) {
		Map<String, Object> resultMap = new HashMap<>();
		String resultCode = "";
		String resultDesc = "";

		SnsFavoriteExample favoriteExample = new SnsFavoriteExample();
		favoriteExample.createCriteria().andStoreIdEqualTo(storeId).andMemberIdEqualTo(memberId)
				.andTypeEqualTo(SnsFavorite.TYPE_COMMENT).andObjectIdEqualTo(commentId);
		List<SnsFavorite> snsFavorites = snsFavoriteMapper.selectByExample(favoriteExample);

		this.snsFavoriteMapper.deleteByExample(favoriteExample);

		if (isLike == true) {
			if (CollectionUtils.isEmpty(snsFavorites)) {
				this.snsCommentMapper.addOrRemoveLike(commentId, true);
				resultCode = "00";
				resultDesc = "点赞成功";
			} else {
				resultCode = "01";
				resultDesc = "已点赞过";
			}

			SnsFavorite snsFavorite = new SnsFavorite();
			snsFavorite.setStoreId(storeId);
			snsFavorite.setMemberId(memberId);
			snsFavorite.setType(SnsFavorite.TYPE_COMMENT);
			snsFavorite.setObjectId(commentId);

			this.snsFavoriteMapper.insertSelective(snsFavorite);

		} else {
			if (CollectionUtils.isNotEmpty(snsFavorites)) {
				this.snsCommentMapper.addOrRemoveLike(commentId, false);
			}
		}

		resultMap.put("resultCode", resultCode);
		resultMap.put("resultDesc", resultDesc);

		return resultMap;
	}

	@Override
	public void comment(SnsComment comment, Integer postId, Member member) {
		Map<String, SnsCommentInterceptor> map = SpringContextHolder.getBeans(SnsCommentInterceptor.class);
		if (MapUtils.isEmpty(map)) {
			this.addObj(comment);
			return;
		}

		Collection<SnsCommentInterceptor> inteceptorList = map.values();
		List<SnsCommentInterceptor> list = new ArrayList<>();
		CollectionUtils.addAll(list, inteceptorList.iterator());
		
		SnsPost snsPost = this.snsPostService.queryObjById(postId);
		for (int i = 0; CollectionUtils.isNotEmpty(list) && i < list.size(); i++) {
			if (!list.get(i).preHandle(snsPost, comment, member)) {
				return;
			}
		}

		SnsCommentService snsCommentService = SpringContextHolder.getBean("snsCommentService");
		snsCommentService.addObj(comment);
//		this.addObj(comment);

		for (int i = list.size() - 1; CollectionUtils.isNotEmpty(list) && i >= 0; i--) {
			list.get(i).afterCompletion(snsPost,comment, member);
		}
	}

}
