package com.mcoding.base.member.web.controller.department;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.department.DepartmentAddress;
import com.mcoding.base.member.bean.department.DepartmentAddressExample;
import com.mcoding.base.member.service.department.DepartmentAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="公司地址")
@Controller
@RequestMapping("departmentAddress")
public class DepartmentAddressController {
    @Resource
    protected DepartmentAddressService departmentAddressService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("member/departmentAddress/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("member/departmentAddress/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        DepartmentAddress departmentAddress = this.departmentAddressService.queryObjById(id);
        view.addObject("departmentAddress", departmentAddress);
        view.setViewName("member/departmentAddress/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建公司地址")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody DepartmentAddress departmentAddress) {
        JsonResult<String> result = new JsonResult<>();
        this.departmentAddressService.addObj(departmentAddress);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑公司地址")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody DepartmentAddress departmentAddress) {
        if (departmentAddress.getId() == null || departmentAddress.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.departmentAddressService.modifyObj(departmentAddress);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除公司地址")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.departmentAddressService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询公司地址")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<DepartmentAddress> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<DepartmentAddress> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        DepartmentAddressExample example = new DepartmentAddressExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.departmentAddressService.queryObjByPage(example);
    }
}