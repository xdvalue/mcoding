package com.mcoding.base.cms.service.label.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.cms.bean.label.ArticleLabelRef;
import com.mcoding.base.cms.bean.label.ArticleLabelRefExample;
import com.mcoding.base.cms.bean.label.Label;
import com.mcoding.base.cms.bean.label.LabelExample;
import com.mcoding.base.cms.persistence.label.ArticleLabelRefMapper;
import com.mcoding.base.cms.persistence.label.LabelMapper;
import com.mcoding.base.cms.service.label.LabelService;
import com.mcoding.base.core.PageView;

/**
 * LabelServiceImpl
 * 
 * @author acer
 * 
 */
@Service
public class LabelServiceImpl implements LabelService {

	@Resource
	protected LabelMapper labelMapper;

	@Resource
	protected ArticleLabelRefMapper articleLabelRefMapper;

	@Override
	public void addObj(Label label) {
		labelMapper.insertSelective(label);
	}

	@Override
	public void deleteObjById(int labelId) {
		labelMapper.deleteByPrimaryKey(labelId);
	}

	@Override
	public void modifyObj(Label label) {
		labelMapper.updateByPrimaryKeySelective(label);
	}

	@Override
	public List<Label> queryAllObjByExample(LabelExample labelExample) {
		return labelMapper.selectByExample(labelExample);
	}

	@Override
	public Label queryObjById(int labelId) {
		return labelMapper.selectByPrimaryKey(labelId);
	}

	@Override
	public PageView<Label> queryObjByPage(LabelExample example) {
		PageView<Label> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<Label>();
			pageView.setPageNo(1);
			pageView.setPageSize(10);
			example.setPageView(pageView);
		}
		List<Label> list = labelMapper.selectByExampleByPage(example);
		pageView.setQueryResult(list);
		return pageView;
	}

	@Override
	public void addLabelHit(int storeId, String label) {
		this.labelMapper.addLabelByMarkAndStoreId(label, storeId);
	}

	@Transactional
	@Override
	public void addLabelOrModifyLabelHit(int articleId, int storeId,
			List<ArticleLabelRef> labelRefList) {
		ArticleLabelRefExample alrEx = new ArticleLabelRefExample();
		alrEx.createCriteria().andArticleIdEqualTo(articleId);
		this.articleLabelRefMapper.deleteByExample(alrEx);

		for (int i = 0; i < labelRefList.size(); i++) {
			ArticleLabelRef ref = labelRefList.get(i);

			if (StringUtils.isBlank(ref.getLableMark())) {
				continue;
			}

			if (ref.getLabelId() != null && ref.getLabelId() > 0) {
				this.labelMapper.addLabelHitById(ref.getLabelId());
			}

			Label label = this.getLabelByMarkAndStore(ref.getLableMark(),
					storeId);
			if (label != null) {
				this.labelMapper.addLabelHitById(label.getId());

				ref.setLabelId(label.getId());

			} else {
				Label tmp = new Label();
				tmp.setHit(1);
				tmp.setStoreId(storeId);
				tmp.setSeqNum(1);
				tmp.setMark(ref.getLableMark());
				this.labelMapper.insertSelective(tmp);

				ref.setLabelId(tmp.getId());
			}

			ref.setArticleId(articleId);
			ref.setStoreId(storeId);
			this.articleLabelRefMapper.insertSelective(ref);
		}
	}

	private Label getLabelByMarkAndStore(String mark, int storeId) {
		LabelExample labelExample = new LabelExample();
		labelExample.setOrderByClause("create_time ASC");
		labelExample.createCriteria().andMarkEqualTo(mark)
				.andStoreIdEqualTo(storeId);

		List<Label> list = this.labelMapper.selectByExample(labelExample);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
