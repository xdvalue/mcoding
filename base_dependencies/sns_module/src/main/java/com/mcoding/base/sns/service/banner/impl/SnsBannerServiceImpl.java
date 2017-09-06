package com.mcoding.base.sns.service.banner.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.bean.banner.SnsBanner;
import com.mcoding.base.sns.bean.banner.SnsBannerExample;
import com.mcoding.base.sns.persistence.banner.SnsBannerMapper;
import com.mcoding.base.sns.service.banner.SnsBannerService;

@Service("snsBannerService")
public class SnsBannerServiceImpl implements SnsBannerService {
    @Resource
    protected SnsBannerMapper snsBannerMapper;

    @CacheEvict(value={"snsBanner"}, allEntries=true)
    @Override
    public void addObj(SnsBanner t) {
        this.snsBannerMapper.insertSelective(t);
    }

    @CacheEvict(value={"snsBanner"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.snsBannerMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"snsBanner"}, allEntries=true)
    @Override
    public void modifyObj(SnsBanner t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.snsBannerMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="snsBanner", key="'SnsBannerService_' + #root.methodName + '_' +#id")
    @Override
    public SnsBanner queryObjById(int id) {
        return this.snsBannerMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="snsBanner", key="'SnsBannerService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<SnsBanner> queryAllObjByExample(SnsBannerExample example) {
        return this.snsBannerMapper.selectByExample(example);
    }

    @Cacheable(value="snsBanner", key="'SnsBannerService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<SnsBanner> queryObjByPage(SnsBannerExample example) {
        PageView<SnsBanner> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.snsBannerMapper.selectByExampleByPage(example));
        return pageView;
    }
}