package com.mcoding.base.wechat.web.controller.redpack;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.exception.WxErrorException;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="微信红包")
@Controller("wxRedpackSendRecordForView")
@RequestMapping("wxRedpackSendRecord")
public class WxRedpackSendRecordController {
    @Resource
    protected WxRedpackSendRecordService wxRedpackSendRecordService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("wechat/wxRedpackSendRecord/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        ModelAndView view = new ModelAndView("wechat/wxRedpackSendRecord/toMainView");
        view.addObject("nowdate", new Date());
        return view;
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        WxRedpackSendRecord wxRedpackSendRecord = this.wxRedpackSendRecordService.queryObjById(id);
        view.addObject("wxRedpackSendRecord", wxRedpackSendRecord);
        view.setViewName("wechat/wxRedpackSendRecord/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建微信红包")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody WxRedpackSendRecord wxRedpackSendRecord) {
        JsonResult<String> result = new JsonResult<>();
        this.wxRedpackSendRecordService.addObj(wxRedpackSendRecord);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑微信红包")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody WxRedpackSendRecord wxRedpackSendRecord) {
        if (wxRedpackSendRecord.getId() == null || wxRedpackSendRecord.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.wxRedpackSendRecordService.modifyObj(wxRedpackSendRecord);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除微信红包")
    @RequestMapping("service/deleteById")
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.wxRedpackSendRecordService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询微信红包")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<WxRedpackSendRecord> findByPage(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(value="查询月份") String month,
    	    @ApiParam(value="查询状态") String status) throws ParseException {
        PageView<WxRedpackSendRecord> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        WxRedpackSendRecordExample example = new WxRedpackSendRecordExample();
        example.setPageView(pageView);
        WxRedpackSendRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(month)) {
        	Date start = DateUtils.parseDate(month.trim(), "yyyy-MM");
        	criteria.andCreateTimeGreaterThanOrEqualTo(start);
        	criteria.andCreateTimeLessThan(DateUtils.addMonths(start, 1));
        }
        if (StringUtils.isNotBlank(status)) {
			criteria.andStatusEqualTo(status);
		}
        return this.wxRedpackSendRecordService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="统计各个状态下的红包总数")
    @RequestMapping("service/countStatus")
    @ResponseBody
    public JsonResult<Map<String, Integer>> countStatus(@ApiParam(value="查询月份") String month) throws ParseException{
        WxRedpackSendRecordExample example = new WxRedpackSendRecordExample();
        WxRedpackSendRecordExample.Criteria criteria = example.createCriteria();
        
    	Date start = DateUtils.parseDate(month, "yyyy-MM");
    	criteria.andCreateTimeGreaterThanOrEqualTo(start);
    	criteria.andCreateTimeLessThan(DateUtils.addMonths(start, 1));
    	
    	JsonResult<Map<String, Integer>> result = new JsonResult<>();
        result.setData(this.wxRedpackSendRecordService.countStatus(example));
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
    
    @ApiOperation(httpMethod="GET", value="重发红包")
    @RequestMapping("service/sendRedpack")
    @ResponseBody
    public JsonResult<String> sendRedpack(int recordId) throws UnknownHostException, WxErrorException{
    	this.wxRedpackSendRecordService.sendRedpack(recordId);
    	
    	JsonResult<String> result = new JsonResult<>();
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
}