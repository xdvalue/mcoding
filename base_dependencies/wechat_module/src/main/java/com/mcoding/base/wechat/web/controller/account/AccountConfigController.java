package com.mcoding.base.wechat.web.controller.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "微信账号表")
@Controller("accountConfigControllerForView")
@RequestMapping("accountConfig")
public class AccountConfigController {
	@Resource
	protected AccountConfigService accountConfigService;

	@ApiIgnore
	@RequestMapping("service/toAddView")
	public ModelAndView toAddView() {
		return new ModelAndView("wechat/accountConfig/toAddView");
	}

	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView() {
		return new ModelAndView("wechat/accountConfig/toMainView");
	}

	@ApiIgnore
	@RequestMapping("service/toUpdateViewById")
	public ModelAndView toUpdateViewById(int id) {
		ModelAndView view = new ModelAndView();
		AccountConfig accountConfig = this.accountConfigService.queryObjById(id);
		view.addObject("accountConfig", accountConfig);
		view.setViewName("wechat/accountConfig/toAddView");
		return view;
	}
}