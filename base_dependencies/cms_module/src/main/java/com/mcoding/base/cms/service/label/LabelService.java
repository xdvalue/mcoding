package com.mcoding.base.cms.service.label;

import java.util.List;

import com.mcoding.base.cms.bean.label.ArticleLabelRef;
import com.mcoding.base.cms.bean.label.Label;
import com.mcoding.base.cms.bean.label.LabelExample;
import com.mcoding.base.core.BaseService;

/**
 * LabelService
 * 
 * @author acer
 * 
 */
public interface LabelService extends BaseService<Label, LabelExample> {

	/**
	 * 根据标签更新标签的热度
	 * 
	 * @param storeId
	 * @param label
	 */
	void addLabelHit(int storeId, String label);

	/**
	 * 如果标签不存在，就创建标签，如果存在就加标签的热度
	 * @param storeId
	 * @param labelList
	 */
	void addLabelOrModifyLabelHit(int articleId, int storeId, List<ArticleLabelRef> labelList);

}
