package com.mcoding.base.auth.service;

import java.util.List;

import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.bean.RoleRightExample;
import com.mcoding.base.core.BaseService;

/**
 * Created by LiBing on 2014-07-22  17:44
 */
public interface RoleRightService extends BaseService<RoleRight, RoleRightExample> {

    public void deleteObj(RoleRight roleRight);
    
    public List<Role> getRoleByOperatorUrl(String url);
}
