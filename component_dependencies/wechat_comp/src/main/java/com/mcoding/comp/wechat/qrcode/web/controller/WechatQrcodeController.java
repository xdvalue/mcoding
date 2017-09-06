package com.mcoding.comp.wechat.qrcode.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcodeExample;
import com.mcoding.comp.wechat.qrcode.service.WxQrcodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;

@Api(value="微信二维码")
@Controller
@RequestMapping("wxQrcode")
public class WechatQrcodeController {
    @Resource
    protected WxQrcodeService wxQrcodeService;

    @ApiOperation(httpMethod="POST", value="创建微信二维码")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxQrcode wxQrcode) throws NumberFormatException, WxErrorException, IOException {
        JsonResult<String> result = new JsonResult<>();
        if (StringUtils.isBlank(wxQrcode.getName())) {
			throw new CommonException("名字不能为空");
		}
        if (!WxQrcode.QRCODE_TYPE_LIMIT.equals(wxQrcode.getType()) && !WxQrcode.QRCODE_TYPE_UNLIMIT.equals(wxQrcode.getType())) {
			throw new CommonException("微信二维码的类型异常");
		}
        
        if (WxQrcode.QRCODE_TYPE_LIMIT.equals(wxQrcode.getType())) {
        	if (wxQrcode.getValidHours() ==null || wxQrcode.getValidHours() <=0) {
        		wxQrcode.setValidHours(24 * 30);
			}
        	wxQrcode.setValidTime(DateUtils.addHours(new Date(), wxQrcode.getValidHours()));
        	wxQrcode.setSceneStr(String.valueOf(System.currentTimeMillis()/1000));
        	this.wxQrcodeService.createLimitQrcode(wxQrcode);
		}else{
			wxQrcode.setSceneStr(WxQrcode.PREFIX_FOR_UNLIMIT + DateFormatUtils.format(new Date(), "yyyyMMddhhmmss"));
			this.wxQrcodeService.createUnLimitQrcode(wxQrcode);
		}
        
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑微信二维码")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody WxQrcode wxQrcode) {
        if (wxQrcode.getId() == null || wxQrcode.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.wxQrcodeService.modifyObj(wxQrcode);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除微信二维码")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.wxQrcodeService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询微信二维码")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<WxQrcode> findByPage(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength,
    		@ApiParam(name="公众号原始id") @RequestParam(required=true,value="originId") String originId,
    		@ApiParam(value="查询条件") String sSearch ) {
    	
        PageView<WxQrcode> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        
        WxQrcodeExample example = new WxQrcodeExample();
        example.createCriteria().andAccountOriginIdEqualTo(originId);
        example.setPageView(pageView);
        
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.wxQrcodeService.queryObjByPage(example);
    }
    
    /**
     * 
     * @return
     * @throws IOException 
     * @throws WxErrorException 
     * @throws NumberFormatException 
     */
    /*@ApiOperation(httpMethod="POST", value="创建微信二维码")
    @RequestMapping("service/createWxQrcode")
    @ResponseBody
    public JsonResult<String> createWxQrcode(
    		@RequestParam(required=true) String sceneId, 
    		@RequestParam(required=true) String originId, 
    		@RequestParam(required=true) int type, 
    		@RequestParam(required=true) String name, Integer expireSecond) throws NumberFormatException, WxErrorException, IOException{
    	
    	if (WxQrcode.QRCODE_TYPE_LIMIT.equals(type)) {
			this.wxQrcodeService.createLimitQrcode(Integer.valueOf(sceneId), originId, name, expireSecond);
		
    	}else if(WxQrcode.QRCODE_TYPE_UNLIMIT.equals(type)){
			this.wxQrcodeService.createUnLimitQrcode(sceneId, originId, name);
		}
    	
    	JsonResult<String> result = new JsonResult<>();
    	result.setStatus("00");
    	result.setMsg("ok");
    	result.setData("ok");
    	return result;
    }*/
    
}