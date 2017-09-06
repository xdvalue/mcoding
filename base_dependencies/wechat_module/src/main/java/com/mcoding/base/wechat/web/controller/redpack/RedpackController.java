package com.mcoding.base.wechat.web.controller.redpack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("redpack")
public class RedpackController {
	
	/*@RequestMapping("test")
	@ResponseBody
	public JsonResult<String> sendRedpack(@RequestBody WxMpRedpack redpack) throws IllegalArgumentException, IllegalAccessException, UnknownHostException, WxErrorException{
//		AccountConfig accountConfig = WxAccountConfigUtils.getAccountConfigFromThreadLocal();
//		Store store = StoreUtils.getStoreFromThreadLocal();
//		AccountConfig accountConfig = 
		redpackService.sendRedpack(redpack, accountConfig);
		
		JsonResult<String> result = new JsonResult<>();
		result.setMsg("00");
		result.setData(null);
		result.setMsg("ok");
		return result;
	}*/
}
