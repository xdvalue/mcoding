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

public class DicGroupCheckboxListTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static DicGroupItemService dicGroupItemService = SpringContextHolder.getBean("dicGroupItemService");
	private String dicGroupCode;
	private List<String> selectedItemCodeList;
	private List<String> selectedItemValueList;
	private String tagClass;
	private String nameOfAllCheckbox;
	private String id;
	private String onCheckboxClick;

	@Override
	public int doStartTag() throws JspException {

		try {
			createTagHtml();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.doStartTag();
	}

	private void createTagHtml() throws IOException {
		Document document = DocumentHelper.createDocument();
		Element div = this.createRootElement(document);

		List<DicGroupItem> itemList = dicGroupItemService.queryItemsByGroupCode(this.dicGroupCode);
		this.insertCheckboxIntoDiv(div, itemList);
		
		JspWriter out = this.pageContext.getOut();
		document.write(out);
	}

	/**
	 * 创建 根节点
	 * 
	 * @param document
	 * @return
	 */
	private Element createRootElement(Document document) {
		Element div = document.addElement("div");
		if (StringUtils.isNotBlank(id)) {
			div.addAttribute("id", id);
		}
		if (StringUtils.isNotBlank(tagClass)) {
			div.addAttribute("class", tagClass);
		}
		return div;
	}

	private void insertCheckboxIntoDiv(Element rootElement, List<DicGroupItem> itemList) {

		for (int i = 0; CollectionUtils.isNotEmpty(itemList) && i < itemList.size(); i++) {
			Element label = rootElement.addElement("label");
			if (StringUtils.isBlank(this.tagClass)) {
				label.addAttribute("class", "checkbox-inline");
			}
			Element checkbox = label.addElement("input")
					                .addAttribute("type", "checkbox")
//					                .addAttribute("data-labelauty", itemList.get(i).getName())
					                .addAttribute("value", itemList.get(i).getValue());
			label.setText(itemList.get(i).getName());
			if (StringUtils.isNotBlank(nameOfAllCheckbox)) {
				checkbox.addAttribute("name", nameOfAllCheckbox);
			}
			
			if (StringUtils.isNotBlank(this.onCheckboxClick)) {
				checkbox.addAttribute("onClick", this.onCheckboxClick);
			}
			
			this.setCheckedForCode(checkbox, itemList.get(i));
			this.setCheckedForVaule(checkbox, itemList.get(i));
		}
	}
	
	/**
	 * 配置这个 checkbox 是否要选上
	 * @param checkbox
	 * @param dicGroupItem
	 */
	private void setCheckedForCode(Element checkbox, DicGroupItem dicGroupItem){
		if (CollectionUtils.isNotEmpty(this.selectedItemCodeList)) {
			for (int codeIndex = 0; codeIndex < this.selectedItemCodeList.size(); codeIndex++) {
				if (this.selectedItemCodeList.get(codeIndex).equals(dicGroupItem.getCode())) {
					checkbox.addAttribute("checked", "true");
				}
			}
		}
	}
	
	/**
	 * 配置这个 checkbox 是否要选上
	 * @param checkbox
	 * @param dicGroupItem
	 */
	private void setCheckedForVaule(Element checkbox, DicGroupItem dicGroupItem){
		if (CollectionUtils.isNotEmpty(this.selectedItemValueList)) {
			for (int index = 0; index < this.selectedItemValueList.size(); index++) {
				if (this.selectedItemValueList.get(index).equals(dicGroupItem.getValue())) {
					checkbox.addAttribute("checked", "true");
				}
			}
		}
	}

	public String getDicGroupCode() {
		return dicGroupCode;
	}

	public void setDicGroupCode(String dicGroupCode) {
		this.dicGroupCode = dicGroupCode;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getSelectedItemValueList() {
		return selectedItemValueList;
	}

	public void setSelectedItemValueList(List<String> selectedItemValueList) {
		this.selectedItemValueList = selectedItemValueList;
	}

	public List<String> getSelectedItemCodeList() {
		return selectedItemCodeList;
	}

	public void setSelectedItemCodeList(List<String> selectedItemCodeList) {
		this.selectedItemCodeList = selectedItemCodeList;
	}

	public String getOnCheckboxClick() {
		return onCheckboxClick;
	}

	public void setOnCheckboxClick(String onCheckboxClick) {
		this.onCheckboxClick = onCheckboxClick;
	}

	public String getNameOfAllCheckbox() {
		return nameOfAllCheckbox;
	}

	public void setNameOfAllCheckbox(String nameOfAllCheckbox) {
		this.nameOfAllCheckbox = nameOfAllCheckbox;
	}
	
}
