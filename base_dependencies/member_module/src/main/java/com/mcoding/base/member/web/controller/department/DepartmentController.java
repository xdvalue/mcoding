package com.mcoding.base.member.web.controller.department;

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
import com.mcoding.base.member.bean.department.Department;
import com.mcoding.base.member.bean.department.DepartmentExample;
import com.mcoding.base.member.service.department.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="会员_所属部门表")
@Controller
@RequestMapping("memberDepartment")
public class DepartmentController {
    @Resource
    protected DepartmentService memberDepartmentService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView(Integer parentId) {
    	ModelAndView view = new ModelAndView("member/memberDepartment/toAddView");
    	if (parentId !=null && parentId > 0) {
    		Department parent = this.memberDepartmentService.queryObjById(parentId);
    		view.addObject("parent", parent);
		}
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toDepartmentListView")
    public ModelAndView toDepartmentListView(int parentId) {
    	Department department = this.memberDepartmentService.queryObjById(parentId);
    	
    	ModelAndView view =new ModelAndView("member/memberDepartment/toDepartmentListView");
    	view.addObject("parent", department);
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        Department memberDepartment = this.memberDepartmentService.queryObjById(id);
        view.addObject("memberDepartment", memberDepartment);
        
        Department parent = this.memberDepartmentService.queryObjById(memberDepartment.getParentId());
        view.addObject("parent", parent);
        
        view.setViewName("member/memberDepartment/toAddView");
        return view;
    }
    
    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("member/memberDepartment/toMainView");
    }

    @ApiOperation(httpMethod="POST", value="创建会员_所属部门表")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody Department memberDepartment) {
        JsonResult<String> result = new JsonResult<>();
        this.memberDepartmentService.addObj(memberDepartment);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑会员_所属部门表")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody Department memberDepartment) {
        if (memberDepartment.getId() == null || memberDepartment.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.memberDepartmentService.modifyObj(memberDepartment);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    /*@ApiOperation(httpMethod="POST", value="删除会员_所属部门表")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.memberDepartmentService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }*/

    @ApiOperation(httpMethod="GET", value="查询会员_所属部门表")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<Department> findByPage(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询条件") String sSearch,
    		@ApiParam(value="上级id", defaultValue="0") @RequestParam(defaultValue="0") int parentId) {
        PageView<Department> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria cri = example.createCriteria();
        cri.andParentIdEqualTo(parentId);
        
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
        	cri.andCompanyNameLike(sSearch + "%");
        }
        return this.memberDepartmentService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询所有公司")
    @RequestMapping("service/findAllCompany")
    @ResponseBody
    public JsonResult<List<Department>> findAllCompany() {
        
        DepartmentExample example = new DepartmentExample();
        DepartmentExample.Criteria cri = example.createCriteria();
        cri.andDepartmentTypeEqualTo(Department.TYPE_COMPANY);
        
        JsonResult<List<Department>> result = new JsonResult<>();
        result.setData(this.memberDepartmentService.queryAllObjByExample(example));
        result.setStatus("00");
        result.setMsg("ok");
        return result;
    }
}
