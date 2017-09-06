package com.mcoding.base.sns.service.post;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.bean.module.SnsModule;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.bean.post.SnsPostExample;

/**
 * 发帖服务层次
 * 
 * @author WZT
 *
 */
public interface SnsPostService extends BaseService<SnsPost, SnsPostExample> {
	
	/**
	 * 点赞，或者取消点赞
	 * 
	 * @param postId
	 * @param isLike
	 *            true就是点赞，false是取消点赞
	 */
	public void addOrRemoveLike(int storeId, int memberId, int postId, boolean isLike);

	/**
	 * 点衰，或者取消点衰
	 * 
	 * @param postId
	 * @param isLike
	 *            true就是点衰，false是取消点衰
	 */
	public void addOrRemoveDislike(int storeId, int memberId, int postId, boolean isDisLike);

	/**
	 * 发布帖子
	 * 
	 * @param snsPost
	 */
	public void post(SnsPost snsPost, Member member);

	/**
	 * 帖子置顶或者取消置顶
	 * 
	 * @param storeId
	 * @param postId
	 * @param isUp
	 *            true 是置顶，false是取消置顶
	 */
	public void upOrDownTop(int storeId, int postId, boolean isUp);
	
	/**
	 * 帖子首页置顶或者取消置顶
	 * 
	 * @param storeId
	 * @param postId
	 * @param isUp
	 *            true 是置顶，false是取消置顶
	 */
	public void upOrDownTopInHome(int storeId, int postId, boolean isUp);

	/**
	 * 查询用户回复的帖子
	 * 
	 * @param storeId
	 * @param memberId
	 * @return
	 */
	public PageView<SnsPost> queryByMemberComment(int memberId, int storeId, int pageNo, int pageSize);
	
	/**
	 * 因为评论，而更新帖子的信息，包括帖子的最新评论时间，和帖子评论总数
	 * @param postId
	 */
	public void modifyForComment(int postId);

	/**
	 * 审核获取取消审核帖子
	 * @param valueOf
	 * @param isCheck
	 */
	public void checkPost(Integer id, int isCheck);

	/**
	 * 设置帖子为精华帖
	 * @param valueOf
	 * @param isEssence
	 */
	public void setEssencePost(Integer id, int isEssence);

	/**
	 * 迁移帖子去模块
	 * @param idList
	 * @param snsModule
	 */
	public void mvToModule(List<Integer> idList, SnsModule snsModule);

}
