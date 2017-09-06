package com.mcoding.base.auth.utils;

import com.mcoding.base.auth.bean.Role;

/**
 * 授权用的常量
 * @author hzy
 *
 */
public class AuthConstant {
	public static final String SECRET_KEY = "tissonco";
	
	/**系统默认角色**/
	public static final Role DEFAULT_ROLE = new Role(-100, "系统默认角色", 0, "SYS_DEFAULT_ROLE");

}
