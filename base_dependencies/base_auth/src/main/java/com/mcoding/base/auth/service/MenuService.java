package com.mcoding.base.auth.service;

import java.util.List;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.bean.MenuExample;
import com.mcoding.base.core.BaseService;

public interface MenuService extends BaseService<Menu, MenuExample> {

//    List<Menu> queryUserTreeMenu();

    List<Menu> queryTreeListForAdmin(int parentMenuId);
    
    List<Menu> queryTreeListForRoleNames(List<String> roleNames, int parentMenuId);

    Menu queryMenuByURL(String url);

    void editMenu(Menu menu);

}
