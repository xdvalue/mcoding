package com.mcoding.base.member.service.setting;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.member.bean.setting.MemberSettingValue;
import com.mcoding.base.member.bean.setting.MemberSettingValueExample;

public interface MemberSettingValueService extends BaseService<MemberSettingValue, MemberSettingValueExample> {
	
	public void changeOneMemberSettingValue(int memberId, String code, String value, Integer codeModuleTypeSns, int storeId);
	
//	public void changeManyMemberSettingValue(List<MemberSettingValue> valueList, Integer moduleType, int storeId);

	public List<MemberSettingValue> queryObjByMemberId(int memberId, int storeId, int moduleType);
	
	public MemberSettingValue queryObjByMemberId(String settingKeyCode, int memberId, int storeId, int moduleType);
}