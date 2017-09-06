package com.mcoding.base.ui.web.controller.dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.dictionary.DicGroup;
import com.mcoding.base.ui.bean.dictionary.DicGroupItem;
import com.mcoding.base.ui.bean.dictionary.DicGroupItemExample;
import com.mcoding.base.ui.service.dictionary.DicGroupItemService;
import com.mcoding.base.ui.service.dictionary.DicGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="字典组子项")
@Controller
@RequestMapping("dicGroupItem")
public class DicGroupItemController {
	
	@Resource
	protected DicGroupItemService dicGroupItemService;
	
	@Resource
	protected DicGroupService dicGroupService;
	
	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView(int dicGroupId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("base/dictionary/dicGroupItem/toAddView");
		DicGroup dicGroup = this.dicGroupService.queryObjById(dicGroupId);
		
		view.addObject("dicGroup", dicGroup);
		return view;
	}
	
	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toDicGroupById(int id) {
		ModelAndView view = new ModelAndView();
		
		DicGroupItem dicGroupItem = null;
		dicGroupItem = this.dicGroupItemService.queryObjById(id);
		view.addObject("dicGroupItem", dicGroupItem);
		
        DicGroup dicGroup = this.dicGroupService.queryObjById(dicGroupItem.getGroupId());
		view.addObject("dicGroup", dicGroup);
		
		view.setViewName("base/dictionary/dicGroupItem/toAddView");
		return view;
	}

	@ApiIgnore
	@RequestMapping("service/toListPageView")
	public ModelAndView toListPageView(int dicGroupId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("base/dictionary/dicGroupItem/listPageView");
		
		DicGroup dicGroup = this.dicGroupService.queryObjById(dicGroupId);
		view.addObject("dicGroup", dicGroup);
		return view;
	}
	
	@ApiOperation(value="创建字典组子项", httpMethod="POST")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody DicGroupItem dicGroupItem) {
		this.dicGroupItemValidtion(dicGroupItem);
		
		JsonResult<String> result = new JsonResult<>();
		
		this.dicGroupItemService.addObj(dicGroupItem);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(value="编辑字典组子项",httpMethod="POST")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody DicGroupItem dicGroupItem) {
		this.dicGroupItemValidtion(dicGroupItem);
		
		JsonResult<String> result = new JsonResult<>();
		this.dicGroupItemService.modifyObj(dicGroupItem);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@ApiOperation(value="删除字典组子项",httpMethod="POST")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id) {
		
		JsonResult<String> result = new JsonResult<>();
		this.dicGroupItemService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	/**
	 * 验证参数是否 合理
	 * @param dicGroupItem
	 */
	private void dicGroupItemValidtion(DicGroupItem dicGroupItem){
		if(dicGroupItem == null || dicGroupItem.getGroupId() == null || dicGroupItem.getGroupId() ==0 
				|| StringUtils.isBlank(dicGroupItem.getCode())
				|| StringUtils.isBlank(dicGroupItem.getValue())
				|| StringUtils.isBlank(dicGroupItem.getName())){
			throw new CommonException("参数不完整，请填写必要的信息");
		}
		
		DicGroupItemExample example = new DicGroupItemExample();
		DicGroupItemExample.Criteria cri1 = example.createCriteria();
	    cri1.andCodeEqualTo(dicGroupItem.getCode());
	    cri1.andGroupIdEqualTo(dicGroupItem.getGroupId());
		
		if(dicGroupItem.getId() != null && dicGroupItem.getId() > 0){
			cri1.andIdNotEqualTo(dicGroupItem.getId());
		}
		
		
		List<DicGroupItem> list = this.dicGroupItemService.queryAllObjByExample(example);
		if (list.size() > 0 ) {
			throw new CommonException("code必须唯一，该字典组中已经存在相同的code");
		}
	}
	
	@ApiOperation(value="分页查询字典组子项", httpMethod="GET")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<DicGroupItem> findByPage(int dicGroupId,String iDisplayStart, String iDisplayLength, String sSearch){
		if (StringUtils.isBlank(iDisplayStart) || !StringUtils.isNumeric(iDisplayStart)) {
			iDisplayStart = "0";
		}
		if (StringUtils.isBlank(iDisplayLength) || !StringUtils.isNumeric(iDisplayLength)) {
			iDisplayLength = "10";
		}

		PageView<DicGroupItem> pageView = new PageView<>(iDisplayStart, iDisplayLength);

		DicGroupItemExample example = new DicGroupItemExample();
		example.setPageView(pageView);
		
		DicGroupItemExample.Criteria cri1 = example.createCriteria();
		cri1.andGroupIdEqualTo(dicGroupId);
		
		if (StringUtils.isNotBlank(sSearch)) {
			cri1.andNameLike( sSearch + "%");
			
			DicGroupItemExample.Criteria cri2 = example.or();
			cri2.andCodeLike(sSearch + "%");
			cri2.andGroupIdEqualTo(dicGroupId);
		}
		
		return this.dicGroupItemService.queryObjByPage(example);
	}
	
	@ApiOperation(value="根据字典组的code值，查询所有子项", httpMethod="GET")
	@RequestMapping(value={"service/findByDicGroupCode", "front/findByDicGroupCode"})
	@ResponseBody
	public JsonResult<List<DicGroupItem>> findByDicGroupCode(String dicGroupCode){
		JsonResult<List<DicGroupItem>> result = new JsonResult<>();
		List<DicGroupItem> list = this.dicGroupItemService.queryItemsByGroupCode(dicGroupCode);
		
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(list);
		
		return result;
	}
	

}
