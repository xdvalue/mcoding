package com.mcoding.base.ui.web.controller.dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.dictionary.DicGroup;
import com.mcoding.base.ui.bean.dictionary.DicGroupExample;
import com.mcoding.base.ui.bean.dictionary.DicGroupExample.Criteria;
import com.mcoding.base.ui.service.dictionary.DicGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="字典组")
@Controller
@RequestMapping("dicGroup")
public class DicGroupController {
	
	@Resource
	protected DicGroupService dicGroupService;
	
	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("base/dictionary/dicGroup/toAddView");
		return view;
	}

	@ApiIgnore
	@RequestMapping("service/toListPageView")
	public ModelAndView toListPageView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("base/dictionary/dicGroup/listPageView");
		return view;
	}
	
	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toDicGroupById(int id) {
		ModelAndView view = new ModelAndView();
		
		DicGroup dicGroup = null;
		dicGroup = this.dicGroupService.queryObjById(id);
		
		view.addObject("dicGroup", dicGroup);
		view.setViewName("base/dictionary/dicGroup/toAddView");
		return view;
	}
	
	@ApiOperation(httpMethod="POST", value="创建字典组")
	@RequestMapping("service/create")
	@ResponseBody
	public JsonResult<String> create(@RequestBody DicGroup dicGroup) {
		dicGroupValidtion(dicGroup);
		
		JsonResult<String> result = new JsonResult<>();
		this.dicGroupService.addObj(dicGroup);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}

	@ApiOperation(httpMethod="POST", value="编辑字典组")
	@RequestMapping("service/edit")
	@ResponseBody
	public JsonResult<String> edit(@RequestBody DicGroup dicGroup) {
		dicGroupValidtion(dicGroup);
		
		JsonResult<String> result = new JsonResult<>();
		this.dicGroupService.modifyObj(dicGroup);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	@ApiOperation(httpMethod="POST", value="删除字典组")
	@RequestMapping("service/deleteById")
	@ResponseBody
	public JsonResult<String> deleteById(int id){
		JsonResult<String> result = new JsonResult<>();
		this.dicGroupService.deleteObjById(id);
		result.setData(null);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	private void dicGroupValidtion(DicGroup dicGroup){
		if(dicGroup == null || StringUtils.isBlank(dicGroup.getCode())
				|| StringUtils.isBlank(dicGroup.getName())){
			throw new CommonException("参数不完整，请填写必要的信息");
			
		}
		
		DicGroupExample example = new DicGroupExample();
		Criteria cri = example.createCriteria();
		cri.andCodeEqualTo(dicGroup.getCode());
		if (dicGroup.getId() != null && dicGroup.getId()>0) {
			cri.andIdNotEqualTo(dicGroup.getId());
		}
		
		List<DicGroup> list = this.dicGroupService.queryAllObjByExample(example);
		if (list.size() > 0 ) {
			throw new CommonException("code必须唯一，该字典组中已经存在相同的code");
		}
	}
	
	@ApiOperation(httpMethod="GET", value="查询字典组")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public PageView<DicGroup> findByPage(
			@ApiParam(name="iDisplayStart",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
			@ApiParam(name="iDisplayLength",defaultValue="10") String iDisplayLength, 
			@ApiParam(name="sSearch", value="查询条件") String sSearch){
		if (StringUtils.isBlank(iDisplayStart) || !StringUtils.isNumeric(iDisplayStart)) {
			iDisplayStart = "0";
		}
		if (StringUtils.isBlank(iDisplayLength) || !StringUtils.isNumeric(iDisplayLength)) {
			iDisplayLength = "10";
		}

		PageView<DicGroup> pageView = new PageView<>(iDisplayStart, iDisplayLength);

		DicGroupExample example = new DicGroupExample();
		example.setPageView(pageView);
		example.setOrderByClause("code ASC");
		
		
		if (StringUtils.isNotBlank(sSearch)) {
			DicGroupExample.Criteria cri1 = example.createCriteria();
			cri1.andNameLike( sSearch + "%");
			DicGroupExample.Criteria cri2 = example.or();
			cri2.andCodeLike(sSearch + "%");
		}
		
		return this.dicGroupService.queryObjByPage(example);
	}
	
	

}
