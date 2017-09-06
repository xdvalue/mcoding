package com.mcoding.base.ui.service.schedule.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.schedule.ScheduleJob;
import com.mcoding.base.ui.bean.schedule.ScheduleJobExample;
import com.mcoding.base.ui.persistence.schedule.ScheduleJobMapper;
import com.mcoding.base.ui.service.schedule.ScheduleJobService;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
    @Resource
    protected ScheduleJobMapper scheduleJobMapper;

    @CacheEvict(value={"scheduleJob"}, allEntries=true)
    @Override
    public void addObj(ScheduleJob t) {
        this.scheduleJobMapper.insertSelective(t);
    }

    @CacheEvict(value={"scheduleJob"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.scheduleJobMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"scheduleJob"}, allEntries=true)
    @Override
    public void modifyObj(ScheduleJob t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.scheduleJobMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="scheduleJob", key="'ScheduleJobService_' + #root.methodName + '_' +#id")
    @Override
    public ScheduleJob queryObjById(int id) {
        return this.scheduleJobMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="scheduleJob", key="'ScheduleJobService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<ScheduleJob> queryAllObjByExample(ScheduleJobExample example) {
        return this.scheduleJobMapper.selectByExample(example);
    }

    @Cacheable(value="scheduleJob", key="'ScheduleJobService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<ScheduleJob> queryObjByPage(ScheduleJobExample example) {
        PageView<ScheduleJob> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.scheduleJobMapper.selectByExampleByPage(example));
        return pageView;
    }
}