package com.mcoding.base.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.auth.bean.Menu;
import com.mcoding.base.auth.bean.MenuExample;
import com.mcoding.base.auth.bean.Role;
import com.mcoding.base.auth.bean.RoleExample;
import com.mcoding.base.auth.bean.RoleRight;
import com.mcoding.base.auth.bean.RoleRightExample;
import com.mcoding.base.auth.persistence.MenuMapper;
import com.mcoding.base.auth.persistence.RoleMapper;
import com.mcoding.base.auth.service.MenuService;
import com.mcoding.base.auth.service.RoleRightService;
import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;

@Service
public class MenuServiceImpl implements MenuService {
    private static final int MAX_CALLCOUNT = 5;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRightService roleRightService;

    //添加菜单
    @CacheEvict(value="menu", allEntries=true)
    @Override
    public void addObj(Menu menu) {
    	Date date = new Date();
        menu.setCreateTime(date);
        menu.setUpdateTime(date);
//        menuMapper.add(menu);
        menuMapper.insertSelective(menu);
    }

    @CacheEvict(value="menu", allEntries=true)
    @Override
    public void deleteObjById(int menuId) {
    	//递归删除子节点
        deleteChildrenMenu(menuId, 0);
    }

    @CacheEvict(value="menu", allEntries=true)
    @Override
    public void modifyObj(Menu menu) {
    	if (menu == null || menu.getMenuId() == null || menu.getMenuId() ==0) {
			throw new IllegalArgumentException("菜单id不能为空");
		}
    	
    	Date date = new Date();
        menu.setUpdateTime(date);
        menuMapper.updateByPrimaryKeySelective(menu);
    }
    
    @Override
    public void editMenu(Menu menu) {
        if (menu.getMenuId() == null) {
        	this.addObj(menu);
        } else {
        	this.modifyObj(menu);
        }
    }


    @Cacheable(value="menu", key="'MenuService_' +#root.methodName+ '_' + #parentMenuId")
    @Override
    public List<Menu> queryTreeListForAdmin(int parentMenuId) {
    	MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(parentMenuId);
        menuExample.setOrderByClause("sortNo ASC");
        List<Menu> menus = this.menuMapper.selectByExample(menuExample);
        
        for (Menu menu : menus) {
            getChildrenMenu(menu, 0, null);
        }
        return menus;
        
    }
    
    //获取登录用户的菜单Id
    @Cacheable(value="menu", key="'MenuService_' +#root.methodName +'_'+ T(com.mcoding.base.utils.json.JsonUtilsForMcoding).writeValueAsString(#roleNames) +'_'+ #parentMenuId")
    @Override
    public List<Menu> queryTreeListForRoleNames(List<String> roleNames, int parentMenuId) {
    	
        List<String> menuIds = new ArrayList<String>();
        Set<String> menuIdSet = new HashSet<String>();
        for (String roleName : roleNames) {
        	RoleExample roleExample = new RoleExample();
        	roleExample.createCriteria().andRoleNameEqualTo(roleName);
        	
        	List<Role> list = this.roleMapper.selectByExample(roleExample);
        	if (list.size() == 0) {
				continue;
			}else if(list.size() > 1){
				throw new CommonException("有重名的角色，请检查数据");
			}
        	
            Role role = list.get(0);
        	
            RoleRightExample roleRightExample = new RoleRightExample();
            roleRightExample.createCriteria().andRoleIdEqualTo(role.getRoleId());
            
            List<RoleRight> rights = this.roleRightService.queryAllObjByExample(roleRightExample);
            
            for (RoleRight right : rights) {
                boolean isAdd = menuIdSet.add(right.getMenuId() + "");
                if (isAdd) {
                    List<String> parentIds = getParentIds(right.getMenuId());
                    menuIdSet.addAll(parentIds);
                }
            }
        }

        Iterator<String> i = menuIdSet.iterator();
        while (i.hasNext()) {
            menuIds.add(i.next());
        }
        
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(parentMenuId);
        menuExample.setOrderByClause("sortNo ASC");
        List<Menu> menus = this.menuMapper.selectByExample(menuExample);
        
        List<Menu> userMenus = new ArrayList<Menu>();
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getMenuId() + "")) {
                userMenus.add(menu);
                getChildrenMenu(menu, 0, menuIds);
            }
        }
        return userMenus;
    }

    //根据菜单Id递归查询出所有的父级菜单Id
    private List<String> getParentIds(int menuId) {
        List<String> parentIds = new ArrayList<String>();
        //查询菜单是否存在上级菜单
        Menu menu = this.menuMapper.selectByPrimaryKey(menuId);
        if (menu != null) {
            int parentId = menu.getParentId();
            parentIds = getParentIds(parentId);
            parentIds.add(parentId + "");
        }
        return parentIds;
    }

    //递归调用查询所有的子菜单
    private void getChildrenMenu(Menu menu, int callCount, List<String> userMenuIds) {
        callCount++;
        //判断递归深度
        if (callCount > MAX_CALLCOUNT) {
            return;
        }
        
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(menu.getMenuId());
        menuExample.setOrderByClause("sortNo ASC");
        
        List<Menu> menuChildren = this.menuMapper.selectByExample(menuExample);
        
        List<Menu> userMenuChildren = new ArrayList<Menu>();
        for (Menu subMenu : menuChildren) {
            if (userMenuIds == null) {
                getChildrenMenu(subMenu, callCount, userMenuIds);
            } else {
                if (userMenuIds.contains(subMenu.getMenuId() + "")) {
                    getChildrenMenu(subMenu, callCount, userMenuIds);
                    userMenuChildren.add(subMenu);
                }
            }
        }
        if (userMenuIds == null) {
            menu.setChildren(menuChildren);
        } else {
            menu.setChildren(userMenuChildren);
        }
    }

    //递归调用删除所有的子菜单
    private void deleteChildrenMenu(int menuId, int callCount) {
        callCount++;
        //判断递归深度
        if (callCount > MAX_CALLCOUNT) {
            return;
        }
//        List<Menu> menuChildren = menuMapper.queryMenuByParentId(menuId);
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andParentIdEqualTo(menuId);
        menuExample.setOrderByClause("sortNo ASC");
        
        List<Menu> menuChildren = this.menuMapper.selectByExample(menuExample);
        
        for (Menu subMenu : menuChildren) {
            deleteChildrenMenu(subMenu.getMenuId(), callCount);
        }
        
        this.menuMapper.deleteByPrimaryKey(menuId);
    }



    @Cacheable(value="menu", key="'MenuService_' +#root.methodName +'_'+ #url")
    @Override
    public Menu queryMenuByURL(String url) {
    	MenuExample menuExample = new MenuExample();
    	menuExample.createCriteria().andMenuURLEqualTo(url);
    	
    	List<Menu> list = this.menuMapper.selectByExample(menuExample);
    	if (list == null || list.size() == 0) {
			return null;
		}
    	
    	return list.get(0);
    }

    @Cacheable(value="menu", key="'MenuService_' +#root.methodName +'_'+ #id")
	@Override
	public Menu queryObjById(int id) {
		return this.menuMapper.selectByPrimaryKey(id);
	}

	@Cacheable(value="menu", key="'MenuService_' +#root.methodName +'_'+ #example.toJson()")
	@Override
	public List<Menu> queryAllObjByExample(MenuExample example) {
		return this.menuMapper.selectByExample(example);
	}

	@Cacheable(value="menu", key="'MenuService_' +#root.methodName +'_'+ #example.toJson()")
	@Override
	public PageView<Menu> queryObjByPage(MenuExample example) {
		PageView<Menu> pageView = example.getPageView();
		List<Menu> list = this.menuMapper.selectByExampleByPage(example);
		pageView.setQueryResult(list);
		return pageView;
	}

}
