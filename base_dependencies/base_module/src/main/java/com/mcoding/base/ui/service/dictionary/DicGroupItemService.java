package com.mcoding.base.ui.service.dictionary;

import java.util.List;

import com.mcoding.base.core.BaseService;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.bean.dictionary.DicGroupItemExample;

public interface DicGroupItemService extends BaseService<DicGroupItem, DicGroupItemExample>{
	
	public List<DicGroupItem> queryItemsByGroupCode(String dicGroupCode);
	
	public DicGroupItem queryItems(String dicGroupCode, String dicGroupItemCode);
	
	public DicGroupItem queryItemByValue(String dicGroupCode, String dicGroupItemValue);
	
	public void deleteItemsByGroupId(int dicGroupId);

}
