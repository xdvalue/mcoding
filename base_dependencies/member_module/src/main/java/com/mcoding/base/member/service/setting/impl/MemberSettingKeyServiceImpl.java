package com.mcoding.base.member.service.setting.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.setting.MemberSettingKey;
import com.mcoding.base.member.bean.setting.MemberSettingKeyExample;
import com.mcoding.base.member.persistence.setting.MemberSettingKeyMapper;
import com.mcoding.base.member.service.setting.MemberSettingKeyService;

@Service("memberSettingKeyService")
public class MemberSettingKeyServiceImpl implements MemberSettingKeyService {
    @Resource
    protected MemberSettingKeyMapper memberSettingKeyMapper;

    @CacheEvict(value={"memberSettingKey"}, allEntries=true)
    @Override
    public void addObj(MemberSettingKey t) {
        this.memberSettingKeyMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberSettingKey"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberSettingKeyMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberSettingKey"}, allEntries=true)
    @Override
    public void modifyObj(MemberSettingKey t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberSettingKeyMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberSettingKey", key="'MemberSettingKeyService_' + #root.methodName + '_' +#id")
    @Override
    public MemberSettingKey queryObjById(int id) {
        return this.memberSettingKeyMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberSettingKey", key="'MemberSettingKeyService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberSettingKey> queryAllObjByExample(MemberSettingKeyExample example) {
        return this.memberSettingKeyMapper.selectByExample(example);
    }

    @Cacheable(value="memberSettingKey", key="'MemberSettingKeyService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberSettingKey> queryObjByPage(MemberSettingKeyExample example) {
        PageView<MemberSettingKey> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberSettingKeyMapper.selectByExampleByPage(example));
        return pageView;
    }
}