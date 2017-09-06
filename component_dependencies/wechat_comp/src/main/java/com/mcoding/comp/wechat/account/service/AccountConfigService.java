package com.mcoding.comp.wechat.account.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.bean.AccountConfigExample;

public interface AccountConfigService{
	
	public void addObj(AccountConfig t);

    public void deleteObjById(int id);

    public void modifyObj(AccountConfig t);

    public AccountConfig queryObjById(int id);
    
    public List<AccountConfig> queryAllObjByExample(AccountConfigExample example);

    public PageView<AccountConfig> queryObjByPage(AccountConfigExample example);
	
	public AccountConfig queryByOriginId(String code);
	
	@Deprecated
	public AccountConfig queryByRequest(HttpServletRequest request);
	
	public AccountConfig queryByRequest(String schemem, String serverName, Integer port,  String requestUri);

}
