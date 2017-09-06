package com.mcoding.base.sns.service.post.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.sns.bean.favorite.SnsFavorite;
import com.mcoding.base.sns.bean.favorite.SnsFavoriteExample;
import com.mcoding.base.sns.bean.post.SnsPostExtInfo;
import com.mcoding.base.sns.bean.post.SnsPostExtInfoExample;
import com.mcoding.base.sns.persistence.favorite.SnsFavoriteMapper;
import com.mcoding.base.sns.persistence.post.SnsPostExtInfoMapper;
import com.mcoding.base.sns.service.post.SnsPostExtInfoService;

@Service("snsPostExtInfoService")
public class SnsPostExtInfoServiceImpl implements SnsPostExtInfoService {
    @Resource
    protected SnsPostExtInfoMapper snsPostExtInfoMapper;
    
    @Resource
	protected SnsFavoriteMapper snsFavoriteMapper;
    
    @CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
    @Override
    public SnsPostExtInfo addObj(int postId){
    	SnsPostExtInfo snsPostExtInfo = new SnsPostExtInfo();
    	snsPostExtInfo.setCommentNum(0);
    	snsPostExtInfo.setDislikeNum(0);
    	snsPostExtInfo.setLikeNum(0);
    	snsPostExtInfo.setViewNum(0);
    	snsPostExtInfo.setPostId(postId);
    	this.snsPostExtInfoMapper.insertSelective(snsPostExtInfo);
    	return snsPostExtInfo;
    }

    @Cacheable(value="snsPostExtInfo", key="'SnsPostExtInfoService_' + #root.methodName + '_'+ #postId")
	@Override
	public SnsPostExtInfo queryObjByPostId(int postId) {
		SnsPostExtInfoExample example = new SnsPostExtInfoExample();
		example.createCriteria().andPostIdEqualTo(postId);
		
		List<SnsPostExtInfo> list = this.snsPostExtInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	@CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
	@Override
	public void addViewNum(int postId, int viewNum){
		SnsPostExtInfo snsPostExtInfo = this.queryObjByPostId(postId);
		if (snsPostExtInfo == null) {
			snsPostExtInfo = this.addObj(postId);
		}
		
		this.snsPostExtInfoMapper.addViewNumByPostId(postId, viewNum);
	}
	
	@CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
	@Override
	public void addCommentNum(int postId){
		SnsPostExtInfo snsPostExtInfo = this.queryObjByPostId(postId);
		if (snsPostExtInfo == null) {
			snsPostExtInfo = this.addObj(postId);
		}
		
		this.snsPostExtInfoMapper.addCommentNumByPostId(postId);
	}
	
	/**
	 * 点赞，或者取消点赞
	 * 
	 * @param postId
	 * @param isLike
	 *            true就是点赞，false是取消点赞
	 * @return 
	 */
	@CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
	@Transactional
	@Override
	public void addOrRemoveLike(int storeId, int memberId, int postId, boolean isLike){

		SnsFavoriteExample snsFavoriteExample = new SnsFavoriteExample();
		snsFavoriteExample.createCriteria()
		                  .andStoreIdEqualTo(storeId)
		                  .andMemberIdEqualTo(memberId)
				          .andObjectIdEqualTo(postId)
				          .andTypeEqualTo(SnsFavorite.TYPE_POST);
		
		List<SnsFavorite> snsFavorites = this.snsFavoriteMapper.selectByExample(snsFavoriteExample);

		if (isLike == true) {
			if (CollectionUtils.isEmpty(snsFavorites)) {
//				this.snsPostMapper.addOrRemoveLike(postId, isLike);
				
				SnsFavorite snsFavorite = new SnsFavorite();
				snsFavorite.setStoreId(storeId);
				snsFavorite.setMemberId(memberId);
				snsFavorite.setType(SnsFavorite.TYPE_POST);
				snsFavorite.setObjectId(postId);

				this.snsFavoriteMapper.insertSelective(snsFavorite);
				
				if (this.queryObjByPostId(postId) == null) {
					this.addObj(postId);
				}
				
				this.snsPostExtInfoMapper.addLikeNumByPostId(postId, true);
				
			} else {
				throw new CommonException("已经点赞了");
			}
		} else {
			
			this.snsFavoriteMapper.deleteByExample(snsFavoriteExample);
			
			if (CollectionUtils.isNotEmpty(snsFavorites)) {
				this.snsPostExtInfoMapper.addLikeNumByPostId(postId, false);
			}
		}
	}
	
	/**
	 * 点衰，或者取消点衰
	 * 
	 * @param postId
	 * @param isLike
	 *            true就是点衰，false是取消点衰
	 */
	public void addOrRemoveDislike(int storeId, int memberId, int postId, boolean isDisLike){
		//TODO
	}

    /*@CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
    @Override
    public void addObj(SnsPostExtInfo t) {
        this.snsPostExtInfoMapper.insertSelective(t);
    }

    @CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.snsPostExtInfoMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"snsPostExtInfo"}, allEntries=true)
    @Override
    public void modifyObj(SnsPostExtInfo t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.snsPostExtInfoMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="snsPostExtInfo", key="'SnsPostExtInfoService_' + #root.methodName + '_' +#id")
    @Override
    public SnsPostExtInfo queryObjById(int id) {
        return this.snsPostExtInfoMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="snsPostExtInfo", key="'SnsPostExtInfoService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<SnsPostExtInfo> queryAllObjByExample(SnsPostExtInfoExample example) {
        return this.snsPostExtInfoMapper.selectByExample(example);
    }

    @Cacheable(value="snsPostExtInfo", key="'SnsPostExtInfoService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<SnsPostExtInfo> queryObjByPage(SnsPostExtInfoExample example) {
        PageView<SnsPostExtInfo> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.snsPostExtInfoMapper.selectByExampleByPage(example));
        return pageView;
    }*/
}