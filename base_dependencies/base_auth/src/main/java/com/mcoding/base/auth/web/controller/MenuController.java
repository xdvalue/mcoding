package com.mcoding.base.auth.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.service.MenuService;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.JsonResult;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/addMenu")
    @ResponseBody
    public JsonResult<String> addMenu(Menu menu) {
//        return menuService.addObj(menu);
    	this.menuService.addObj(menu);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public JsonResult<String> deleteMenu(int menuId) {
        menuService.deleteObjById(menuId);
        
        JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/modifyMenu")
    @ResponseBody
    public JsonResult<String> modifyMenu(Menu menu) {
//        return menuService.modifyObj(menu);
    	this.menuService.modifyObj(menu);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/navigateMenuManager.html")
    public ModelAndView toNavigateViewRole() {
        ModelAndView mav = new ModelAndView();
        Menu menu = menuService.queryMenuByURL("navigateMenuManager.html");
        String menuId = "";
        if (menu != null) {
            menuId = menu.getMenuId() + "";
        }
        mav.addObject("navigateMenuId", menuId);
        mav.setViewName("base/menu/menuViewManager");
        return mav;
    }

    @RequestMapping(value="/queryMenuTreeData", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Menu> queryMenuTreeData(HttpServletResponse response) {
    	response.setHeader("Content-Type", "application/json");
    	int parentMenuId = 0;
    	if(SpringSecurityUtils.isManagerLoginUser()){
    		return menuService.queryTreeListForAdmin(parentMenuId);
    		
    	}else{
    		List<String> roleNames = SpringSecurityUtils.getLoginUserRoleNameList();
    		return menuService.queryTreeListForRoleNames(roleNames, parentMenuId);
    		
    	}
    	
        
    }
    
    
    @RequestMapping("/editMenu")
    @ResponseBody
    public JsonResult<String> editMenu(Menu menu) {
        menuService.editMenu(menu);
        
        JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

}
