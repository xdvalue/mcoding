package com.mcoding.base.ui.service.region.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.region.Region;
import com.mcoding.base.ui.bean.region.RegionExample;
import com.mcoding.base.ui.persistence.region.RegionMapper;
import com.mcoding.base.ui.service.region.RegionService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("regionService")
public class RegionServiceImpl implements RegionService {
    @Resource
    protected RegionMapper regionMapper;

    @CacheEvict(value={"region"}, allEntries=true)
    @Override
    public void addObj(Region t) {
        this.regionMapper.insertSelective(t);
    }

    @CacheEvict(value={"region"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.regionMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"region"}, allEntries=true)
    @Override
    public void modifyObj(Region t) {
        if (t.getRegionId() == null || t.getRegionId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.regionMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="region", key="'RegionService_' + #root.methodName + '_' +#id")
    @Override
    public Region queryObjById(int id) {
        return this.regionMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="region", key="'RegionService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<Region> queryAllObjByExample(RegionExample example) {
        return this.regionMapper.selectByExample(example);
    }

    @Cacheable(value="region", key="'RegionService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<Region> queryObjByPage(RegionExample example) {
        PageView<Region> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.regionMapper.selectByExampleByPage(example));
        return pageView;
    }
}