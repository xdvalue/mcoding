package com.mcoding.base.dst.service.promotion.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.promotion.DstPromotProductPoster;
import com.mcoding.base.dst.bean.promotion.DstPromotProductPosterExample;
import com.mcoding.base.dst.persistence.promotion.DstPromotProductPosterMapper;
import com.mcoding.base.dst.service.promotion.DstPromotProductPosterService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("dstPromotProductPosterService")
public class DstPromotProductPosterServiceImpl implements DstPromotProductPosterService {
    @Resource
    protected DstPromotProductPosterMapper dstPromotProductPosterMapper;

    @CacheEvict(value={"dstPromotProductPoster"}, allEntries=true)
    @Override
    public void addObj(DstPromotProductPoster t) {
        this.dstPromotProductPosterMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstPromotProductPoster"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstPromotProductPosterMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstPromotProductPoster"}, allEntries=true)
    @Override
    public void modifyObj(DstPromotProductPoster t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstPromotProductPosterMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstPromotProductPoster", key="'DstPromotProductPosterService_' + #root.methodName + '_' +#id")
    @Override
    public DstPromotProductPoster queryObjById(int id) {
        return this.dstPromotProductPosterMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstPromotProductPoster", key="'DstPromotProductPosterService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstPromotProductPoster> queryAllObjByExample(DstPromotProductPosterExample example) {
        return this.dstPromotProductPosterMapper.selectByExample(example);
    }

    @Cacheable(value="dstPromotProductPoster", key="'DstPromotProductPosterService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstPromotProductPoster> queryObjByPage(DstPromotProductPosterExample example) {
        PageView<DstPromotProductPoster> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstPromotProductPosterMapper.selectByExampleByPage(example));
        return pageView;
    }
}