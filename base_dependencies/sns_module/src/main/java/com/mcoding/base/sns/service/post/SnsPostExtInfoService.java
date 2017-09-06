package com.mcoding.base.sns.service.post;

import com.mcoding.base.sns.bean.post.SnsPostExtInfo;

public interface SnsPostExtInfoService{
//	public void addObj(SnsPostExtInfo t);
	
//    public void modifyObj(SnsPostExtInfo t);

//    public SnsPostExtInfo queryObjById(int id);
    
    public SnsPostExtInfo queryObjByPostId(int postId);

	SnsPostExtInfo addObj(int postId);

	void addViewNum(int postId, int viewNum);

	void addCommentNum(int postId);

	void addOrRemoveLike(int storeId, int memberId, int postId, boolean isLike);
    
//    public List<T> queryAllObjByExample(Example example);
//
//    public PageView<T> queryObjByPage(Example example);
}