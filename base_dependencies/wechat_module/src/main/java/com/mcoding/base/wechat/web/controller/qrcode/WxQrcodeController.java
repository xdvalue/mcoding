package com.mcoding.base.wechat.web.controller.qrcode;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.JsonResult;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.service.WxQrcodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="微信二维码")
@Controller
@RequestMapping("wxQrcode")
public class WxQrcodeController {
    @Resource
    protected WxQrcodeService wxQrcodeService;
    
    @Resource
    protected AccountConfigService accountConfigService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView(@RequestParam(required=true, value="originId") String originId) {
    	ModelAndView view = new ModelAndView("wechat/wxQrcode/toAddView");
    	view.addObject("account", this.accountConfigService.queryByOriginId(originId));
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView(@RequestParam(required=true, value="originId") String originId) {
    	ModelAndView view = new ModelAndView("wechat/wxQrcode/toMainView");
    	view.addObject("account", this.accountConfigService.queryByOriginId(originId));
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id, @RequestParam(required=true, value="originId") String originId) {
        ModelAndView view = new ModelAndView();
        WxQrcode wxQrcode = this.wxQrcodeService.queryObjById(id);
        view.addObject("wxQrcode", wxQrcode);
        view.addObject("account", this.accountConfigService.queryByOriginId(originId));
        view.setViewName("wechat/wxQrcode/toAddView");
        return view;
    }
    /*
    @ApiOperation(httpMethod="POST", value="创建微信二维码")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxQrcode wxQrcode) {
        JsonResult<String> result = new JsonResult<>();
        this.wxQrcodeService.addObj(wxQrcode);
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
    public PageView<WxQrcode> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<WxQrcode> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        WxQrcodeExample example = new WxQrcodeExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.wxQrcodeService.queryObjByPage(example);
    }*/
}