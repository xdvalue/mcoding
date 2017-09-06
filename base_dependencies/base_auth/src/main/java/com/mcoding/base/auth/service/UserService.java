package com.mcoding.base.auth.service;

import java.util.List;

import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserExample;
import com.mcoding.base.core.BaseService;
import com.mcoding.base.core.PageView;

/**
 * Created by libing on 2014-06-02  09:02.
 */
public interface UserService extends BaseService<User, UserExample> {

    User login(String loginName, String pwd);

    void modifyPwd(String oldPwd, String newPwd) throws Exception;

    public List<User> queryAllUserIsDisabled();

    //根据组织架构ID查询该架构下的相关人员
    PageView<User> queryUserByOrgIdData(String iDisplayStart, String iDisplayLength,String sSearch);

    User findusername(String params);
    
    void disableMembers(Integer userId, Integer status);
    
	Integer resetPwd(String password);

	Integer checkOldPwd(String oldPwd);

    //启用、禁用用户
    void disableUserById(String userId);

}
