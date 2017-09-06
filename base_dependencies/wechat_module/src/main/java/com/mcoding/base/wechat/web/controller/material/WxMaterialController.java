package com.mcoding.base.wechat.web.controller.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialFileBatchGetResult;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="微信素材管理")
@Controller
@RequestMapping("wxMaterial")
public class WxMaterialController {
	
	@Autowired
	protected AccountConfigService accountConfigService;
	
	@ApiIgnore
	@RequestMapping("service/toMainView")
	public ModelAndView toMainView(@RequestParam(required=true, value="originId") String originId){
		ModelAndView view = new ModelAndView();
		view.setViewName("wechat/material/toMainView");
		view.addObject("account", this.accountConfigService.queryByOriginId(originId));
		return view;
	}
	
	@ApiOperation(httpMethod = "GET", value = "查找素材")
	@RequestMapping("service/findByPage")
	@ResponseBody
	public JsonResult<Object> findMaterialByPage(
			@ApiParam(name="分页索引", defaultValue = "0") @RequestParam(defaultValue = "0") int pageNo,
			@ApiParam(name="每页的数量", defaultValue = "10") @RequestParam(defaultValue = "10") int pageSize,
			@ApiParam(name="查询条件") String sSearch,
			@ApiParam(name="类型", required=true) String type,
			@ApiParam(name="微信公众号原始id") @RequestParam(required=true, value="originId") String originId) throws NumberFormatException, WxErrorException{
		
		AccountConfig account = accountConfigService.queryByOriginId(originId);
		WxMpServiceImpl wxMpService = (WxMpServiceImpl) WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		int offset = pageNo * pageSize;
		WxMpMaterialFileBatchGetResult materials = wxMpService.getMaterialService().materialFileBatchGet(type, offset, pageSize);
		
		JsonResult<Object> result = new JsonResult<>();
		result.setStatus("00");
		result.setMsg("ok");
		result.setData(materials);
		return result;
		
//		switch (type) {
//		case WxConsts.MATERIAL_NEWS:
//			WxMpMaterialNewsBatchGetResult materialNews = wxMpService.materialNewsBatchGet(Integer.valueOf(iDisplayStart), Integer.valueOf(iDisplayLength));
////			pageView.put("queryResult", materialNews.getItems());
////			pageView.put("iTotalRecords", materialNews.getTotalCount());
////			pageView.put("iTotalDisplayRecords", materialNews.getTotalCount());
//			
//			pageView.setQueryResult(MaterialNews.transform(materialNews.getItems()));
//			break;
//		default:
//			WxMpMaterialFileBatchGetResult materials = wxMpService.materialFileBatchGet(type, Integer.valueOf(iDisplayStart), Integer.valueOf(iDisplayLength));
////			pageView.put("queryResult", materials.getItems());
////			pageView.put("iTotalRecords", materials.getTotalCount());
////			pageView.put("iTotalDisplayRecords", materials.getTotalCount());
//			break;
//		}
//		return pageView;
	}

}
