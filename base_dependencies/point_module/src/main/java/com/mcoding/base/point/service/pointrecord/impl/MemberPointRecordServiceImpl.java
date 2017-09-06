package com.mcoding.base.point.service.pointrecord.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecord;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecordExample;
import com.mcoding.base.point.persistence.pointrecord.MemberPointRecordMapper;
import com.mcoding.base.point.service.pointrecord.MemberPointRecordService;


@Service("memberPointRecordService")
public class MemberPointRecordServiceImpl implements MemberPointRecordService {
    @Resource
    protected MemberPointRecordMapper memberPointRecordMapper;

    @CacheEvict(value={"memberPointRecord"}, allEntries=true)
    @Override
    public void addObj(MemberPointRecord t) {
        this.memberPointRecordMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberPointRecord"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberPointRecordMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberPointRecord"}, allEntries=true)
    @Override
    public void modifyObj(MemberPointRecord t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberPointRecordMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberPointRecord", key="'MemberPointRecordService_' + #root.methodName + '_' +#id")
    @Override
    public MemberPointRecord queryObjById(int id) {
        return this.memberPointRecordMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberPointRecord", key="'MemberPointRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberPointRecord> queryAllObjByExample(MemberPointRecordExample example) {
        return this.memberPointRecordMapper.selectByExample(example);
    }

    @Cacheable(value="memberPointRecord", key="'MemberPointRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberPointRecord> queryObjByPage(MemberPointRecordExample example) {
        PageView<MemberPointRecord> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberPointRecordMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="memberPointRecord", key="'MemberPointRecordService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public int countByExample(MemberPointRecordExample example) {
		return this.memberPointRecordMapper.countByExample(example);
	}

	@Override
	public int sumPointByExample(MemberPointRecordExample example) {
		return this.memberPointRecordMapper.sumPointByExample(example);
	}

}