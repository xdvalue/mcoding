package com.mcoding.base.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.service.MenuService;
import com.mcoding.base.auth.service.RoleService;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

/**
 * Created by libing on 2014-06-02  08:57.
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/addRole")
    @ResponseBody
    public JsonResult<String> addRole(Role role){
//        return roleService.addObj(role);
    	this.roleService.addObj(role);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public JsonResult<String> deleteRole(int roleId){
        
    	this.roleService.deleteObjById(roleId);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/modifyRole")
    @ResponseBody
    public JsonResult<String> modifyRole(Role role){
    	
    	this.roleService.modifyObj(role);
        
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/navigateRole.html")
    public ModelAndView toNavigateViewRole(){
        ModelAndView mav = new ModelAndView();
        Menu menu = menuService.queryMenuByURL("navigateRole.html");
        String menuId = "";
        if(menu!=null){
            menuId = menu.getMenuId() + "";
        }
        mav.addObject("navigateMenuId", menuId);
        mav.setViewName("base/role/roleView");
        return mav;
    }

    @RequestMapping("/distributionRightsView.html")
    public ModelAndView distributionRightsView(String roleId){
        ModelAndView mav = new ModelAndView();
        Role role = roleService.queryRoleById(roleId);
        mav.addObject("role", role);
        mav.setViewName("base/role/distributionRightsView");
        return mav;
    }

    @RequestMapping("/queryAllRoleByPage")
    @ResponseBody
    public PageView<Role> queryAllRoleByPage(String iDisplayStart, String iDisplayLength){
        return roleService.queryObjByPage(SpringSecurityUtils.isManagerLoginUser(), iDisplayStart, iDisplayLength);
    }
}
