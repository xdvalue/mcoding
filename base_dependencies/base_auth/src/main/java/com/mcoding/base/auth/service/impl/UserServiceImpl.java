package com.mcoding.base.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.auth.bean.User;
import com.mcoding.base.auth.bean.UserExample;
import com.mcoding.base.auth.persistence.UserMapper;
import com.mcoding.base.auth.service.UserService;
import com.mcoding.base.auth.utils.AuthConstant;
import com.mcoding.base.auth.utils.SpringSecurityUtils;
import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.utils.encryption.DES;

/**
 * Created by libing on 2014-06-02  09:05.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String loginName, String pwd) {
        User user = null;
        try {
            DES des = new DES(AuthConstant.SECRET_KEY);
            Map<String, String> params = new HashMap<String, String>();
            params.put("loginName", loginName);
            params.put("password", des.encrypt(pwd));
            
            UserExample userExample = new UserExample();
            userExample.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(pwd);
            
            List<User> userList = this.queryAllObjByExample(userExample);
            if(userList == null || userList.size() ==0){
            	return user;
            }
            
            user = userList.get(0);
            user.setPassword("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void modifyPwd(String oldPwd, String newPwd) throws Exception {
    	DES des = new DES(AuthConstant.SECRET_KEY);
        String encOldPwd = des.encrypt(oldPwd);

        //获取用户信息
        String userId = "";
        String loginName = "";
        //获取用户的菜单权限信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //判断是否是超级管理员账号,如果是则直接跳过
        Object obj = authentication.getPrincipal();
        if (obj instanceof User) {
            User user = (User) obj;
            userId = user.getUserId() + "";
            loginName = user.getLoginName();
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("loginName", loginName);
        params.put("password", encOldPwd);
        
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                   .andUserIdEqualTo(Integer.valueOf(userId))
                   .andLoginNameEqualTo(loginName)
                   .andPasswordEqualTo(encOldPwd);
        
        List<User> list = this.queryAllObjByExample(userExample);
        
        if(CollectionUtils.isEmpty(list)){
        	throw new CommonException("原始密码不正确!");
        }
        
        String encNewPwd = des.encrypt(newPwd);
        User tmp = new User();
        tmp.setUserId(Integer.valueOf(userId));
        tmp.setPassword(encNewPwd);
        this.userMapper.updateByPrimaryKeySelective(tmp);
        
    }

    @Override
    public List<User> queryAllUserIsDisabled() {
    	UserExample userExample = new UserExample();
    	userExample.createCriteria().andIsDisabledEqualTo(Constant.YES_INT);
    	return this.userMapper.selectByExample(userExample);
    }

//    @Override
    public PageView<User> queryUserByOrgIdData(String iDisplayStart, String iDisplayLength,String sSearch) {
        PageView<User> pageView = new PageView<User>(iDisplayStart, iDisplayLength);
        UserExample userExample = new UserExample();
        userExample.setPageView(pageView);
        
        if (StringUtils.isNotBlank(sSearch)) {
			userExample.createCriteria().andNickNameLike(sSearch + "%");
		}
        List<User> users = this.userMapper.selectByExampleByPage(userExample);
        pageView.setQueryResult(users);
        return pageView;
    }

    @Override
    public void deleteObjById(int id) {
    	if (id <= 0) {
			throw new IllegalArgumentException("id 不能少于等于0");
		}
    	if (id == 1) {
        	throw new CommonException("无法删除超级管理员");
        }
        this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void modifyObj(User user) {
    	Date date = new Date();
        //修改用户
        user.setUpdateTime(date);
        this.userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更改密码--修改密码
     */
    @Override
    public Integer resetPwd(String password) {
        Integer result = 1;
        try {
            //修改用户密码
            DES des = new DES(AuthConstant.SECRET_KEY);
            String enPassword = des.encrypt(password);
            Integer userId = SpringSecurityUtils.getLoginUserId();
            
            User tmp = new User();
            tmp.setUserId(userId);
            tmp.setPassword(enPassword);
//            this.userMapper.updateByPrimaryKeySelective(tmp);
            this.modifyObj(tmp);
            
        } catch (Exception e) {
            e.printStackTrace();
            result = 1;
        }

        return result;

    }

    /**
     * 更改密码--验证旧密码
     */
    @Override
    public Integer checkOldPwd(String oldPwd) {
        Integer result = 0;
        try {
        	DES des = new DES(AuthConstant.SECRET_KEY);
        	String enPassword = des.encrypt(oldPwd);
        	String userName = SpringSecurityUtils.getLoginUserName();
        	
        	UserExample userExample = new UserExample();
        	userExample.createCriteria().andLoginNameEqualTo(userName).andPasswordEqualTo(enPassword);
        	
        	int count = this.userMapper.countByExample(userExample);
        	if (count > 0) {
				result = 1;
			}
        	
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }

        return result;

    }

    @Override
    public void disableUserById(String userId) {
    	User user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (user == null) {
			throw new CommonException("启用/禁用用户出错:找不到该用户");
		}
    	
        User tmp = new User();
    	tmp.setUserId(user.getUserId());
    	
    	Integer isDisable = user.getIsDisabled();
        if (Constant.YES_INT.equals(isDisable)) {
        	tmp.setIsDisabled(Constant.NO_INT);
        	
        } else {
        	tmp.setIsDisabled(Constant.YES_INT);
        }
        
        this.userMapper.updateByPrimaryKeySelective(tmp);
    	
    }

    @Override
    public User queryObjById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findusername(String params) {
    	UserExample userExample = new UserExample();
    	userExample.createCriteria().andLoginNameEqualTo(params);
    	
    	List<User> list = this.userMapper.selectByExample(userExample);
    	if (list == null || list.size() ==0) {
			return null;
		}
        return list.get(0);
    }

    @Override
    public void disableMembers(Integer userId, Integer status) {
    	User user = new User();
    	user.setUserId(userId);
    	user.setIsDisabled(status);
    	this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public void addObj(User user) {
    	Date date = new Date();
        //加密密码
        DES des = null;
        String encPwd = null;
		try {
			des = new DES(AuthConstant.SECRET_KEY);
			encPwd = des.encrypt(user.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        user.setPassword(encPwd);
        if(user.getUserId()==null){
        	//新增用户
            user.setCreateTime(date);
            user.setUpdateTime(date);
            this.userMapper.insertSelective(user);
        }else {//修改用户
        	user.setUpdateTime(date);
        	this.userMapper.updateByPrimaryKeySelective(user);
		}
    }

	@Override
	public List<User> queryAllObjByExample(UserExample example) {
		return this.userMapper.selectByExample(example);
	}

	@Override
	public PageView<User> queryObjByPage(UserExample example) {
		PageView<User> pageView = example.getPageView();
		if (pageView == null) {
			pageView = new PageView<>(1, 10);
			example.setPageView(pageView);
		}
		pageView.setQueryResult(this.userMapper.selectByExampleByPage(example));
		return pageView;
	}

}
