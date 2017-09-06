package com.mcoding.base.ui.web.jspTag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

/**
 * 数据字典的jsptag
 * 
 * @author hzy
 * 
 */
public class DicGroupSelectTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_CLASS = "form-control input-inline input-medium";

	private static DicGroupItemService dicGroupItemService = SpringContextHolder.getBean("dicGroupItemService");

	private String dicGroupCode;
	private String selectedItemCode;
	private String selectedItemValue;
	private String tagClass;
	private String name;
	private String id;
	private String onChange;
	private boolean required;

	@Override
	public int doStartTag() throws JspException {
		try {
			createTagHtml();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private void createTagHtml() throws IOException {
		Document document = DocumentHelper.createDocument();
		Element select = this.createSelectElement(document);

		List<DicGroupItem> itemList = dicGroupItemService.queryItemsByGroupCode(this.dicGroupCode);
		this.insertOptionByDicGroupItem(select, itemList);

		JspWriter out = this.pageContext.getOut();
		document.write(out);
	}

	/**
	 * 创建 下拉菜单
	 * 
	 * @param document
	 * @return
	 */
	private Element createSelectElement(Document document) {
		Element select = document.addElement("select");
		if (StringUtils.isNotBlank(id)) {
			select.addAttribute("id", id);
		}
		if (StringUtils.isNotBlank(name)) {
			select.addAttribute("name", name);
		}
		if (StringUtils.isNotBlank(tagClass)) {
			select.addAttribute("class", tagClass);
		} else {
			select.addAttribute("class", DEFAULT_CLASS);
		}
		if (StringUtils.isNotBlank(onChange)) {
			select.addAttribute("onChange", onChange);
		}
		if (required) {
			select.addAttribute("required", "required");
		}
		return select;
	}

	/**
	 * 根据字典的值，插入option 到select
	 * 
	 * @param dicGroupItems
	 *            字典子项列表
	 * @param selectedItemCode
	 *            选中的子项
	 */
	private void insertOptionByDicGroupItem(Element select, List<DicGroupItem> itemList) {
		select.addElement("option").addAttribute("value", "").setText("请选择");

		for (int i = 0; CollectionUtils.isNotEmpty(itemList) && i < itemList.size(); i++) {
			Element option = select.addElement("option").addAttribute("value", itemList.get(i).getValue())
					.addAttribute("code", itemList.get(i).getCode());

			if (StringUtils.isNotBlank(selectedItemCode) && this.selectedItemCode.equals(itemList.get(i).getCode())) {
				option.addAttribute("selected", "selected");
			}
			if (StringUtils.isNotBlank(selectedItemValue)
					&& this.selectedItemValue.equals(itemList.get(i).getValue())) {
				option.addAttribute("selected", "selected");
			}
			option.setText(itemList.get(i).getName());
		}
	}

	public String getDicGroupCode() {
		return dicGroupCode;
	}

	public void setDicGroupCode(String dicGroupCode) {
		this.dicGroupCode = dicGroupCode;
	}

	public String getSelectedItemCode() {
		return selectedItemCode;
	}

	public void setSelectedItemCode(String selectedItemCode) {
		this.selectedItemCode = selectedItemCode;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public String getSelectedItemValue() {
		return selectedItemValue;
	}

	public void setSelectedItemValue(String selectedItemValue) {
		this.selectedItemValue = selectedItemValue;
	}

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
}
