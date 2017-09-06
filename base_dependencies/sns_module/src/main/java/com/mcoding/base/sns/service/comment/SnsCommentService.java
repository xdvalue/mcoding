package com.mcoding.base.sns.service.comment;

import java.util.List;
import java.util.Map;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.sns.bean.comment.SnsComment;
import com.mcoding.base.sns.bean.comment.SnsCommentExample;

public interface SnsCommentService {
	
	public void addObj(SnsComment SnsComment);

    public void deleteObjById(int id);
    public void modifyObj(SnsComment SnsComment);
    public SnsComment queryObjById(int id);
    public PageView<SnsComment> queryObjByPage(SnsCommentExample example);
	
    public List<SnsComment> queryAllObjByExample(SnsCommentExample example);
    
	/**
	 * 给文章添加评论
	 * @param comment
	 * @param postId
	 * @param member
	 */
	public void comment(SnsComment comment, Integer postId, Member member);

	/**
	 * 点赞，或者取消点赞
	 * 
	 * @param memberId
	 * @param commentId
	 * @param isLike
	 */
	public Map<String, Object> addOrRemoveLike(int storeId, int memberId, int commentId, boolean isLike);

	public PageView<SnsComment> queryAllObjByPostId(int postId);

}
