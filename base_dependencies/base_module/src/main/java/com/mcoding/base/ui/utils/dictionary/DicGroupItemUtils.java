package com.mcoding.base.ui.utils.dictionary;

import java.util.List;

import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

public class DicGroupItemUtils {
	
	public static DicGroupItem getDicGroupItemByCode(String dicGroupCode, String dicGroupItemCode){
		DicGroupItemService service = SpringContextHolder.getOneBean(DicGroupItemService.class);
		return service.queryItems(dicGroupCode, dicGroupItemCode);
	}
	
	public static DicGroupItem getDicGroupItemByValue(String dicGroupCode, String dicGroupItemValue){
		DicGroupItemService service = SpringContextHolder.getOneBean(DicGroupItemService.class);
		return service.queryItemByValue(dicGroupCode, dicGroupItemValue);
	}
	
	public static List<DicGroupItem> getDicGroupItemsByGroupCode(String dicGroupCode){
		DicGroupItemService service = SpringContextHolder.getOneBean(DicGroupItemService.class);
		return service.queryItemsByGroupCode(dicGroupCode);
	}

}
