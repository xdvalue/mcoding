package com.mcoding.base.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.auth.bean.Operator;
import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.service.OperatorService;
import com.mcoding.base.auth.service.RoleRightService;
import com.mcoding.base.auth.service.RoleService;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

/**
 * Created by LiBing on 2014-07-17  14:16
 */
@Controller
public class OperatorController {

    @Resource
    private OperatorService operatorService;

    @Resource
    private RoleRightService rightService;
    
    @Resource
    private RoleService roleService;

    @RequestMapping("/addOperator")
    @ResponseBody
    public JsonResult<String> addOperator(Operator operator) {
    	this.operatorService.addObj(operator);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/deleteOperator")
    @ResponseBody
    public JsonResult<String> deleteOperator(int id) {
    	this.operatorService.deleteObjById(id);
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    	
    }

    @RequestMapping("/modifyOperator")
    @ResponseBody
    public JsonResult<String> modifyOperator(Operator operator,Integer operId) {
        operator.setOperId(operId);
//    	return operatorService.modifyObj(operator);
        this.operatorService.modifyObj(operator);
        
        JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }

    @RequestMapping("/queryMenuOperatorList")
    @ResponseBody
    public PageView<Operator> queryMenuOperatorList(String menuId) {
        return operatorService.queryOperatorsByPage(menuId);
    }
    

    @RequestMapping("/queryRoleOperatorList")
    @ResponseBody
    public PageView<Operator> queryRoleOperatorList(int roleId, int menuId) {
        return operatorService.queryRoleOperatorsByMenuId(roleId, menuId, this.getLoginUserRoleIds());
    }
    
    private List<Integer> getLoginUserRoleIds() {
        List<Integer> roleIds = new ArrayList<Integer>();
        List<String> roleNames = SpringSecurityUtils.getLoginUserRoleNameList();
        for (String roleName : roleNames) {
//            Role role = roleMapper.queryByRoleName(roleName);
        	RoleExample roleExample = new RoleExample();
        	roleExample.createCriteria().andRoleNameEqualTo(roleName);
        	
        	List<Role> list = this.roleService.queryAllObjByExample(roleExample);
        	if (list.size() == 0) {
				continue;
			}else if(list.size() > 1){
				throw new CommonException("有重名的角色，请检查数据");
			}
        	
            Role role = list.get(0);
            
            if (role != null) {
                roleIds.add(role.getRoleId());
            }
        }
        return roleIds;
    }

    @RequestMapping("/authorizedOperator")
    @ResponseBody
    public JsonResult<String> authorizedOperator(int roleId, int menuId, int operId, boolean authorized) {
        RoleRight right = new RoleRight();
        right.setRoleId(roleId);
        right.setMenuId(menuId);
        right.setOperId(operId);
        
        if(authorized){ //授权
            rightService.addObj(right);
        }else{ //取消授权
            rightService.deleteObj(right);
        }
        
        JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }
}
