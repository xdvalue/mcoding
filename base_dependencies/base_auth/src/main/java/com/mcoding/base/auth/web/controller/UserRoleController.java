package com.mcoding.base.auth.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.service.MenuService;
import com.mcoding.base.auth.service.RoleService;
import com.mcoding.base.auth.service.UserRoleService;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;

/**
 * Created by libing on 2014-06-02 08:57.
 */
@Controller
public class UserRoleController {
	@Autowired
	private UserRoleService rightService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	@Autowired
	private DefaultTransactionDefinition def;
	@Autowired
	private PlatformTransactionManager transactionManager;

	@RequestMapping("/addUserRoleRight")
	@ResponseBody
	public JsonResult<String> addUserRoleRight(String roleId, String userId) {
		UserRole userRole = new UserRole();
		userRole.setRoleId(Integer.parseInt(roleId));
		userRole.setUserId(Integer.parseInt(userId));
		rightService.addObj(userRole);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/deleteUserRoleRight")
	@ResponseBody
	public JsonResult<String> deleteUserRight(int rightId) {
		rightService.deleteObjById(rightId);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/modifyUserRoleRight")
	@ResponseBody
	public JsonResult<String> modifyUserRight(String rightId, String roleId, String userId) {
		UserRole userRole = new UserRole();
		userRole.setRightId(Integer.parseInt(rightId));
		userRole.setRoleId(Integer.parseInt(roleId));
		userRole.setUserId(Integer.parseInt(userId));
		rightService.modifyObj(userRole);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/navigateRightManager")
	public ModelAndView navigateRightView() {
		ModelAndView mav = new ModelAndView();
		Menu menu = menuService.queryMenuByURL("navigateRightManager.html");
		String menuId = "";
		if (menu != null) {
			menuId = menu.getMenuId() + "";
		}
		mav.addObject("navigateMenuId", menuId);
		mav.setViewName("base/role/rightViewManager");
		return mav;
	}

	@RequestMapping("/navigateSelectRole")
	public ModelAndView navigateSelectRoleView(String configUserId) {
		ModelAndView mav = new ModelAndView();
		
		RoleExample example = new RoleExample();
		example.setOrderByClause("createTime DESC");
		List<Role> roleList = roleService.queryAllObjByExample(example);
//		List<Role> roleList = roleService.queryUserOwnAndCreateRoles(SpringSecurityUtils.isManagerLoginUser(), SpringSecurityUtils.getLoginUserId());
		List<UserRole> userRoleList = rightService.queryUserRoleList(Integer.parseInt(configUserId));
		for (Role role : roleList) {
			for (UserRole ur : userRoleList) {
				if (role.getRoleId().equals(ur.getRoleId())) {
					role.setSelected("selected");
					break;
				}
			}
		}
		mav.addObject("roleList", roleList);
		
		User user = userService.queryObjById(Integer.parseInt(configUserId));
		mav.addObject("configUserId", user.getUserId());
		mav.addObject("nickName", user.getNickName());
		mav.setViewName("base/role/selectRole");
		return mav;
	}

	@RequestMapping("/addUserRoleList")
	@ResponseBody
	public JsonResult<String> addUserRoleList(String roleIds, int userId) {
		JsonResult<String> result = new JsonResult<String>();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			// 先清除用户原有的角色
			rightService.deleteAllUserRole(userId);
			if (StringUtils.isNotBlank(roleIds)) {
				String[] userRoleIds = roleIds.split(",");
				for (String roleId : userRoleIds) {
					UserRole userRole = new UserRole();
					userRole.setUserId(userId);
					userRole.setRoleId(Integer.parseInt(roleId));
					rightService.addObj(userRole);
				}
			}
			transactionManager.commit(status);
			result.setStatus("00");
			result.setData("ok");
			result.setMsg("ok");

		} catch (Exception e) {
			transactionManager.rollback(status);
			throw new CommonException("设置角色失败，异常："+ e.getMessage());
		}
		return result;
	}
}
