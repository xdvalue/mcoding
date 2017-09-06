package com.mcoding.comp.wechat.menu.web.controller;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.JsonResult;
import com.mcoding.base.utils.json.JsonUtilsForMcoding;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import io.swagger.annotations.Api;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;

/**
 * 微信自定义菜单控制器
 * Created by Benson on 2016/8/21.
 */
@Api("微信自定义菜单接口")
@Controller
@RequestMapping("wechatMenu")
public class WechatMenuController {
    @Resource
    protected AccountConfigService accountConfigService;

    /**
	 * 获取菜单
	 * @param originId
	 * @return
	 * @throws WxErrorException
	 * @author Leiming
	 */
	@RequestMapping(value="/service/menuGet", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult<WxMpMenu> menuGet(@RequestParam(required=true, value="originId") String originId) throws WxErrorException{
//		AccountConfig account = accountConfigService.queryObjById(accountId);
		AccountConfig account = accountConfigService.queryByOriginId(originId);
		WxMpServiceImpl wxMpService = (WxMpServiceImpl) WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		WxMpMenu wxMenu = wxMpService.getMenuService().menuGet();
		
		JsonResult<WxMpMenu> result = new JsonResult<>();
		result.setData(wxMenu);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
	/**
     * 创建或更新菜单
     * @param originId
     * @param wxMenu
     * @return
     * @throws Exception
     */
    @RequestMapping(value="service/create",  produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult<WxMpMenu> create(@RequestParam(required=true, value="originId") String originId, @RequestBody WxMpMenu wxMenu) throws Exception {		
		
		AccountConfig account = accountConfigService.queryByOriginId(originId);
		
		WxMpServiceImpl wxMpService = (WxMpServiceImpl) WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		if (CollectionUtils.isEmpty(wxMenu.getMenu().getButtons())) {
			wxMpService.getMenuService().menuDelete();
		}else{
			wxMpService.getMenuService().menuCreate(JsonUtilsForMcoding.writeValueAsString(wxMenu.getMenu()));
		}
		
		WxMpMenu newMenu = wxMpService.getMenuService().menuGet();
		
		JsonResult<WxMpMenu> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(newMenu);
		return result;
	}
}
