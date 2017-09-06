package com.mcoding.base.member.service.setting.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.setting.MemberSettingKey;
import com.mcoding.base.member.bean.setting.MemberSettingKeyExample;
import com.mcoding.base.member.bean.setting.MemberSettingValue;
import com.mcoding.base.member.bean.setting.MemberSettingValueExample;
import com.mcoding.base.member.persistence.setting.MemberSettingValueMapper;
import com.mcoding.base.member.service.setting.MemberSettingKeyService;
import com.mcoding.base.member.service.setting.MemberSettingValueService;

@Service("memberSettingValueService")
public class MemberSettingValueServiceImpl implements MemberSettingValueService {
    @Resource
    protected MemberSettingValueMapper memberSettingValueMapper;
    
    @Resource
    protected MemberSettingKeyService memberSettingKeyService;

    @CacheEvict(value={"memberSettingValue"}, allEntries=true)
    @Override
    public void addObj(MemberSettingValue t) {
        this.memberSettingValueMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberSettingValue"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberSettingValueMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberSettingValue"}, allEntries=true)
    @Override
    public void modifyObj(MemberSettingValue t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.memberSettingValueMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberSettingValue", key="'MemberSettingValueService_' + #root.methodName + '_' +#id")
    @Override
    public MemberSettingValue queryObjById(int id) {
        return this.memberSettingValueMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberSettingValue", key="'MemberSettingValueService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberSettingValue> queryAllObjByExample(MemberSettingValueExample example) {
        return this.memberSettingValueMapper.selectByExample(example);
    }

    @Cacheable(value="memberSettingValue", key="'MemberSettingValueService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberSettingValue> queryObjByPage(MemberSettingValueExample example) {
        PageView<MemberSettingValue> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberSettingValueMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Transactional
    @CacheEvict(value={"memberSettingValue"}, allEntries=true)
	@Override
	public void changeOneMemberSettingValue(int memberId, String code, String value, Integer moduleType, int storeId) {
    	if (StringUtils.isBlank(code) || StringUtils.isBlank(value) || moduleType == null ) {
    		throw new IllegalArgumentException("没有code/value/moduleType都不能为空");
		}
    	
    	MemberSettingKeyExample keyExample = new MemberSettingKeyExample();
    	keyExample.createCriteria()
    	          .andSettingCodeEqualTo(code)
    	          .andModuleTypeEqualTo(moduleType);
    	
    	List<MemberSettingKey> list = this.memberSettingKeyService.queryAllObjByExample(keyExample);
    	if (CollectionUtils.isEmpty(list)) {
			throw new IllegalArgumentException("没有code:" + code + "，的会员配置项");
		}
    	
    	MemberSettingKey settingKey = list.get(0);
    	
    	MemberSettingValueExample valueExample = new MemberSettingValueExample();
    	valueExample.createCriteria()
    	            .andMemberIdEqualTo(memberId)
    	            .andStoreIdEqualTo(storeId)
    	            .andSettingKeyIdEqualTo(settingKey.getId());
    	this.memberSettingValueMapper.deleteByExample(valueExample);
    	
    	MemberSettingValue settingvalue = new MemberSettingValue();
    	settingvalue.setMemberId(memberId);
    	settingvalue.setSettingKeyId(settingKey.getId());
    	settingvalue.setSettingValue(value);
    	settingvalue.setStoreId(storeId);
    	settingvalue.setSettingKeyCode(settingKey.getSettingCode());
//    	settingvalue.set
    	this.addObj(settingvalue);
	}

	@Transactional
	@Cacheable(value="memberSettingValue", key="'MemberSettingValueService_' + #root.methodName + '_'+ #memberId + '_' + #storeId + '_' +#moduleType")
	@Override
	public List<MemberSettingValue> queryObjByMemberId(int memberId, int storeId, int moduleType) {
		MemberSettingKeyExample keyExample = new MemberSettingKeyExample();
		keyExample.createCriteria().andModuleTypeEqualTo(moduleType);
		
		List<MemberSettingKey> keyList = this.memberSettingKeyService.queryAllObjByExample(keyExample);
		
		List<MemberSettingValue> valueList = new ArrayList<>(keyList.size());
		for(int i=0; CollectionUtils.isNotEmpty(keyList) && i<keyList.size(); i++){
//			idList.add(keyList.get(i).getId());
			Integer keyId = keyList.get(i).getId();
			MemberSettingValueExample valueExample = new MemberSettingValueExample();
			valueExample.createCriteria()
			            .andMemberIdEqualTo(memberId)
			            .andStoreIdEqualTo(storeId)
			            .andSettingKeyIdEqualTo(keyId);
			
			List<MemberSettingValue> values = this.memberSettingValueMapper.selectByExample(valueExample);
			if (CollectionUtils.isEmpty(values)) {
				valueList.add(this.getDefalutSetting(keyList.get(i), memberId, storeId));
			}else{
				valueList.add(values.get(0));
			}
		}
		
		return valueList;
	}

	private MemberSettingValue getDefalutSetting(MemberSettingKey memberSettingKey, int memberId, int storeId) {
		MemberSettingValue value = new MemberSettingValue();
		value.setMemberId(memberId);
		value.setStoreId(storeId);
		value.setSettingKeyId(memberSettingKey.getId());
		value.setSettingKeyCode(memberSettingKey.getSettingCode());
		value.setSettingValue(memberSettingKey.getSettingDefaultValue());
		return value;
	}

	@Override
	public MemberSettingValue queryObjByMemberId(String settingKeyCode, int memberId, int storeId, int moduleType) {
		MemberSettingValueExample valueExample = new MemberSettingValueExample();
		valueExample.createCriteria().andStoreIdEqualTo(storeId).andMemberIdEqualTo(memberId).andSettingKeyCodeEqualTo(settingKeyCode);
		
		List<MemberSettingValue> settingList = this.memberSettingValueMapper.selectByExample(valueExample);
		if (CollectionUtils.isNotEmpty(settingList)) {
			return settingList.get(0);
		}
		
		MemberSettingKeyExample keyExample = new MemberSettingKeyExample();
		keyExample.createCriteria().andSettingCodeEqualTo(settingKeyCode).andModuleTypeEqualTo(moduleType);
		List<MemberSettingKey> keyList = this.memberSettingKeyService.queryAllObjByExample(keyExample);
		if (CollectionUtils.isEmpty(keyList)) {
			return null;
		}
		return this.getDefalutSetting(keyList.get(0), memberId, storeId);
		
	}

//	@Override
//	public void changeManyMemberSettingValue(List<MemberSettingValue> valueList, Integer moduleType, int storeId) {
//		// TODO Auto-generated method stub
//		
//	}
}