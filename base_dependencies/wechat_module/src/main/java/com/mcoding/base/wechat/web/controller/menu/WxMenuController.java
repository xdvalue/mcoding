package com.mcoding.base.wechat.web.controller.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import io.swagger.annotations.Api;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="微信公众号菜单管理")
@Controller
@RequestMapping("wxMenu")
public class WxMenuController {

	@Autowired
	protected AccountConfigService accountConfigService;
	
	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView(@RequestParam(required=true, value="originId") String originId){
		ModelAndView view = new ModelAndView();
//		view.setViewName("wechat/menus/menuCreate");
		view.setViewName("wechat/menus/toMainView");
		view.addObject("account", this.accountConfigService.queryByOriginId(originId));
		return view;
	}
	
	/**
	 * 获取菜单
	 * @param wxMenu
	 * @return
	 * @throws WxErrorException
	 * @author Leiming
	 */
	@RequestMapping(value="/service/menuGet", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult<WxMpMenu> menuGet(@RequestParam(required=true, value="originId") String originId) throws WxErrorException{
//		AccountConfig account = accountConfigService.queryObjById(accountId);
		AccountConfig account = accountConfigService.queryByOriginId(originId);
		
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		WxMpMenu wxMenu = wxMpService.getMenuService().menuGet();
		
		JsonResult<WxMpMenu> result = new JsonResult<>();
		result.setData(wxMenu);
		result.setMsg("ok");
		result.setStatus("00");
		return result;
	}
	
    /**
     * 创建菜单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="service/create",  produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JsonResult<WxMenu> create(@RequestParam(required=true, value="originId") String originId, 
			@RequestBody WxMenu wxMenu) throws Exception {		
		
		AccountConfig account = accountConfigService.queryByOriginId(originId);
        WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		wxMpService.getMenuService().menuCreate(wxMenu);
		
		JsonResult<WxMenu> result = new JsonResult<WxMenu>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(wxMenu);
		return result;
	}
    
}
