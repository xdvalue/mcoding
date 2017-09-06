package com.mcoding.base.auth.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserExample;
import com.mcoding.base.auth.bean.UserRole;
import com.mcoding.base.auth.service.MenuService;
import com.mcoding.base.auth.service.RoleService;
import com.mcoding.base.auth.service.UserRoleService;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;

/**
 * Created by libing on 2014-06-02 08:57.
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService rightService;

	@RequestMapping("/queryUserDetail")
	@ResponseBody
	public JsonResult<User> queryUserDetail(int userId) {

		User user = userService.queryObjById(userId);
		JsonResult<User> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(user);

		return result;
	}

	@RequestMapping("/addEmp")
	@ResponseBody
	public JsonResult<String> addUser(User user) {
		userService.addObj(user);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/disableEmp")
	@ResponseBody
	public JsonResult<String> disableEmp(String userId) {
		userService.disableUserById(userId);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/modifyEmp")
	@ResponseBody
	public JsonResult<String> modifyEmp(User user) {

		userService.modifyObj(user);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/modifyUser")
	@ResponseBody
	public JsonResult<String> modifyUser(@RequestBody User user, HttpSession session) {

		userService.modifyObj(user);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/modifyUserPwd")
	@ResponseBody
	public JsonResult<String> modifyUserPwd(String oldPwd, String newPwd) throws Exception {
		userService.modifyPwd(oldPwd, newPwd);

		JsonResult<String> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData("ok");
		return result;
	}

	@RequestMapping("/navigateUser.html")
	public ModelAndView toNavigateViewUser() {
		ModelAndView mav = new ModelAndView();
		Menu menu = menuService.queryMenuByURL("navigateUser.html");
		String menuId = "";
		if (menu != null) {
			menuId = menu.getMenuId() + "";
		}
		mav.addObject("navigateMenuId", menuId);
		mav.setViewName("base/user/userView");
		return mav;
	}

	@RequestMapping("/queryAllUser")
	@ResponseBody
	public Map<String, Object> toViewUserData(int pageNo, int pageSize) {
		return null;// userService.queryList(pageNo, pageSize);
	}

	@RequestMapping("/main.html")
	public ModelAndView toNavigateViewMain() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main");
		return mav;
	}

	@RequestMapping("/queryAllUserList")
	@ResponseBody
	public List<User> queryAllDeptList() {
		UserExample example = new UserExample();
		return userService.queryAllObjByExample(example);
	}

	@RequestMapping("/login.html")
	public ModelAndView navigateLoginView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("base/user/login");
		return mav;
	}

	@RequestMapping("/loginout.html")
	public ModelAndView loginout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		session.invalidate();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("base/user/login");
		return mav;
	}

	/**
	 * 登陆失败跳转方法
	 */
	@RequestMapping("/loginFail.html")
	public ModelAndView loginFail(HttpServletRequest request) {
		String tip = request.getParameter("tip");

		if (StringUtils.isBlank(tip)) {
			tip = "用户名或密码错误/账号已被禁止，请重新输入";
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("tip", tip);
		mav.setViewName("base/user/login");
		return mav;
	}

	// /**
	// * 员工信息
	// * */
	// @RequestMapping("/selectemployee.html")
	// @ResponseBody
	// public Object accountDisabled(String userId) {
	//
	// return mav;
	// }

	/**
	 * 登陆成功后，跳转首页 *
	 */
	@RequestMapping("/index.html")
	public ModelAndView navigateIndexView(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 放置userId,loginName 在session
		User user = SpringSecurityUtils.getLoginUser();
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("loginUserIsManager", SpringSecurityUtils.isManagerLoginUser());
		session.setAttribute("nickName", user.getNickName());

		// 获取所有菜单
		List<Menu> menuTree = null;
		int parentMenuId = 1;
		if (SpringSecurityUtils.isManagerLoginUser()) {
			menuTree = menuService.queryTreeListForAdmin(parentMenuId);

		} else {
			List<String> roleNames = SpringSecurityUtils.getLoginUserRoleNameList();
			menuTree = menuService.queryTreeListForRoleNames(roleNames, parentMenuId);
		}

		mav.addObject("menuTree", menuTree);
		mav.setViewName("index");
		mav.setViewName("main/index");
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/refreshOperatorRight")
	@ResponseBody
	public Map<String, Set<String>> refreshOperatorRight(HttpSession session) {
		Map<String, Set<String>> rightMap = null;
		Object obj = session.getAttribute("userRight");
		if (obj instanceof Map) {
			rightMap = (Map<String, Set<String>>) obj;
		} else {
			rightMap = roleService.queryUserOperatorAction(SpringSecurityUtils.isManagerLoginUser(), SpringSecurityUtils.getLoginUserRoleNameList());
			session.setAttribute("userRight", rightMap);
		}
		if (rightMap.containsKey(null)) {
			rightMap.remove(null);
		}
		return rightMap;
	}

	/*
	 * @RequestMapping("/userLogout.html") public ModelAndView
	 * logout(HttpSession session) { ModelAndView mnv = new ModelAndView();
	 * session.invalidate(); mnv.setViewName("base/user/login"); return mnv; }
	 */

	/**
	 * 用户资料
	 *
	 * @author Cheng
	 */
	@RequestMapping("/userProfile.html")
	public ModelAndView userProfile() {
		ModelAndView mav = new ModelAndView();
		int userId = SpringSecurityUtils.getLoginUserId();
		User user = userService.queryObjById(userId);
		mav.addObject("user", user);
		mav.setViewName("base/user/userInformation");
		return mav;
	}

	/**
	 * 跳转组员列表页面
	 *
	 * @author Tony
	 */
	@RequestMapping("/GroupMembersList.html")
	public ModelAndView toGroupMembersList() {
		ModelAndView mav = new ModelAndView();
		Menu menu = menuService.queryMenuByURL("GroupMembersList.html");
		String menuId = "";
		if (menu != null) {
			menuId = menu.getMenuId() + "";
		}
		mav.addObject("navigateMenuId", menuId);
		mav.addObject("groupMember", "members");
		mav.setViewName("team/GroupMembersList");
		return mav;
	}

	/**
	 * 跳转到忘记密码页面--输入手机号+用户名验证并发送验证码页面
	 *
	 * @author tissue
	 */
	@RequestMapping("/find_password.html")
	public ModelAndView find_password(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate();
		mav.setViewName("base/user/find_password");
		return mav;
	}

	/**
	 * 跳转到忘记密码页面2--输入新密码页面
	 *
	 * @author tissue
	 */
	@RequestMapping("/find_password2.html")
	public ModelAndView find_password2(HttpSession session, Integer userId) {
		ModelAndView mav = new ModelAndView();
//		String code = session.getAttribute("trueCode").toString();
		// 销毁所有会话
		session.invalidate();
		mav.addObject("userId", userId);
		mav.setViewName("base/user/find_password2");
		return mav;
	}

	/**
	 * 修改密码--更改密码(跳转)
	 *
	 * @author tissue
	 */
	@RequestMapping("/changePwd.html")
	public ModelAndView changePwd() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("base/user/changePwd");
		return mav;
	}

	/**
	 * 修改密码--验证旧密码是否输入正确
	 *
	 * @author tissue
	 */
	@ResponseBody
	@RequestMapping("/checkOldPwd")
	public Integer checkOldPwd(String oldPwd) {
		return userService.checkOldPwd(oldPwd);
	}

	/**
	 * 修改密码--更改密码(操作)
	 *
	 * @author tissue
	 */
	@RequestMapping("/doChangePwd.html")
	public ModelAndView doChangePwd(HttpSession session, String password) {
		ModelAndView mav = new ModelAndView();
		userService.resetPwd(password);
		session.invalidate();
		mav.setViewName("base/user/find_password3");
		return mav;
	}

	@RequestMapping("/addGroupMembers.html")
	public ModelAndView addGroupMembers() {
		ModelAndView mav = new ModelAndView();
		// 根据登录用户查找角色数据传递给组员编辑页面
		List<Role> roleList = roleService.queryUserOwnAndCreateRoles(SpringSecurityUtils.isManagerLoginUser(),
				SpringSecurityUtils.getLoginUserId());
		mav.addObject("roleList", roleList);
		mav.setViewName("team/addGroupMember");
		return mav;
	}

	@RequestMapping("/editUser.html")
	public ModelAndView editUser(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 根据录用户查找角色数据传递给组员编辑页面
		List<Role> roleList = roleService.queryUserOwnAndCreateRoles(SpringSecurityUtils.isManagerLoginUser(),
				SpringSecurityUtils.getLoginUserId());
		mav.addObject("roleList", roleList);
		// 传递参数给组员编辑页面
		String memberId = request.getParameter("userId");
		String nickName = request.getParameter("nickName");
		String loginName = request.getParameter("loginName");
		String email = request.getParameter("email");
		String mobilePhone = request.getParameter("mobilePhone");
		String qq = request.getParameter("qq");
		String groupId = request.getParameter("groupId");
		String levelId = request.getParameter("levelId");
		String password = request.getParameter("password");
		mav.addObject("memberId", memberId);
		mav.addObject("nickName1", nickName);
		mav.addObject("loginName", loginName);
		mav.addObject("groupId", groupId);
		mav.addObject("email", email);
		mav.addObject("mobilePhone", mobilePhone);
		mav.addObject("qq", qq);
		mav.addObject("levelId", levelId);
		mav.addObject("password", password);

		User user = userService.queryObjById(Integer.valueOf(memberId));
		List<UserRole> userRoleList = rightService.queryUserRoleList(Integer.valueOf(memberId));
		for (Role role : roleList) {
			for (UserRole ur : userRoleList) {
				if (role.getRoleId() == ur.getRoleId()) {
					role.setSelected("selected");
					break;
				}
			}
		}
		mav.addObject("roleList", roleList);
		mav.addObject("configUserId", user.getUserId());
		mav.addObject("nickName", user.getNickName());
		mav.setViewName("team/addGroupMember");
		return mav;
	}

	@RequestMapping("/sessionTimeOut.html")
	public ModelAndView sessionTimeOut() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/sessionTimeOut");
		return mav;
	}

	@RequestMapping("/refuse.html")
	public ModelAndView refuse() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/refuse");
		return mav;
	}

	/**
	 * 用户绑定
	 * 
	 * @author Moshow
	 */
	@RequestMapping("/userBind.html")
	public ModelAndView userBind() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("base/user/userBind");
		return mav;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/employeeContact.html")
	public ModelAndView navigateOrgManagerView() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("base/organization/employeeList");
		return mav;
	}

	@RequestMapping("/queryUserByOrgIdData")
	@ResponseBody
	public PageView<User> queryOrganizationData(String iDisplayStart, String iDisplayLength, String sSearch) {
		return userService.queryUserByOrgIdData(iDisplayStart, iDisplayLength, sSearch);
	}

	@RequestMapping("/addEmpPage.html")
	public ModelAndView addGroupMembers(String orgId, String editUserId) {
		ModelAndView mav = new ModelAndView();

		User editUser = null;
		if (StringUtils.isNotBlank(editUserId)) {
			editUser = userService.queryObjById(Integer.parseInt(editUserId));
		}

		mav.addObject("editUser", editUser);
		mav.setViewName("base/organization/addEmployee");
		return mav;
	}
	
	/**
	 * 请求查询账户名
	 * @author Tony
	 * */
	@RequestMapping("/queryUserByLoginName")
	@ResponseBody
	public User queryUserByLoginName(String loginName){
		return userService.findusername(loginName);
	}

}
