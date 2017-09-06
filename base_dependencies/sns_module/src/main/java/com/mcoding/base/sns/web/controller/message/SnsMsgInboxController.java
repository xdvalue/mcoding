package com.mcoding.base.sns.web.controller.message;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.JsonResult;
import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.bean.message.CommentMsgInbox;
import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgInbox;
import com.mcoding.base.sns.bean.message.SnsMsgInboxExample;
import com.mcoding.base.sns.service.message.SnsMsgInboxService;
import com.mcoding.base.ui.utils.common.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="收信箱")
@Controller
@RequestMapping("snsMsgInbox")
public class SnsMsgInboxController {
    @Resource
    protected SnsMsgInboxService snsMsgInboxService;

    @ApiIgnore
    @RequestMapping("service/toAddView")
    public ModelAndView toAddView() {
        return new ModelAndView("sns/snsMsgInbox/toAddView");
    }

    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView() {
        return new ModelAndView("sns/snsMsgInbox/toMainView");
    }

    @ApiIgnore
    @RequestMapping("service/toUpdateViewById")
    public ModelAndView toUpdateViewById(int id) {
        ModelAndView view = new ModelAndView();
        SnsMsgInbox snsMsgInbox = this.snsMsgInboxService.queryObjById(id);
        view.addObject("snsMsgInbox", snsMsgInbox);
        view.setViewName("sns/snsMsgInbox/toAddView");
        return view;
    }

    @ApiOperation(httpMethod="POST", value="创建收信箱")
    @RequestMapping("service/create")
    @ResponseBody
    public JsonResult<String> create(@RequestBody SnsMsgInbox snsMsgInbox) {
        JsonResult<String> result = new JsonResult<>();
        this.snsMsgInboxService.addObj(snsMsgInbox);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="编辑收信箱")
    @RequestMapping("service/edit")
    @ResponseBody
    public JsonResult<String> edit(@RequestBody SnsMsgInbox snsMsgInbox) {
        if (snsMsgInbox.getId() == null || snsMsgInbox.getId() <=0) {
            throw new CommonException("id 为空，保存失败");
        }
        JsonResult<String> result = new JsonResult<>();
        this.snsMsgInboxService.modifyObj(snsMsgInbox);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="POST", value="删除收信箱")
    @RequestMapping(value={"service/deleteById", "front/deleteById"})
    @ResponseBody
    public JsonResult<String> deleteById(int id) {
        JsonResult<String> result = new JsonResult<>();
        this.snsMsgInboxService.deleteObjById(id);
        result.setData(null);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }

    @ApiOperation(httpMethod="GET", value="查询收信箱")
    @RequestMapping("service/findByPage")
    @ResponseBody
    public PageView<SnsMsgInbox> findByPage(@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, @ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, @ApiParam(value="查询条件") String sSearch) {
        PageView<SnsMsgInbox> pageView = new PageView<>(iDisplayStart, iDisplayLength);
        SnsMsgInboxExample example = new SnsMsgInboxExample();
        example.setPageView(pageView);
        if (StringUtils.isNotBlank(sSearch)) {
            // TODO Auto-generated method stub
        }
        return this.snsMsgInboxService.queryObjByPage(example);
    }
    
    @ApiOperation(httpMethod="GET", value="查询信息")
    @RequestMapping("front/findById")
    @ResponseBody
    public JsonResult<SnsMsgInbox> findById(int id) {
        
//        return this.snsMsgInboxService.queryObjByPage(example);
    	SnsMsgInbox snsMsgInbox = this.snsMsgInboxService.queryObjById(id);
    	
    	JsonResult<SnsMsgInbox> result = new JsonResult<>();
        result.setData(snsMsgInbox);
        result.setMsg("ok");
        result.setStatus("00");
        return result;
    }
    
    @ApiOperation(httpMethod="GET", value="最新消息的统计数")
    @RequestMapping("front/getTheCountNumForNewMsg")
    @ResponseBody
    public JsonResult<Map<Integer, Integer>> getTheCountNumForNewMsg(HttpSession session){
    	Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");
		
		int countForCommentMsg = this.snsMsgInboxService.countMsg(memberId, storeId, SnsMsg.MSG_TYPE_COMMENT);
		int countForSystemMsg = this.snsMsgInboxService.countMsg(memberId, storeId, SnsMsg.MSG_TYPE_SYSTEM);
		
		Map<Integer, Integer> countResult = new HashMap<>();
		countResult.put(SnsMsg.MSG_TYPE_SYSTEM, countForSystemMsg);
		countResult.put(SnsMsg.MSG_TYPE_COMMENT, countForCommentMsg);
		
		JsonResult<Map<Integer, Integer>> result = new JsonResult<>();
		result.setData(countResult);
		result.setMsg("ok");
		result.setSize(0);
		result.setStatus("00");
		return result;
		
    }
    
    @ApiOperation(httpMethod="GET", value="查询评论我的消息")
    @RequestMapping("front/findCommentMsg")
    @ResponseBody
    public PageView<CommentMsgInbox> findCommentMsg(HttpSession session,
			@ApiParam(value = "页码") @RequestParam(required = true, defaultValue="1") int pageNo,
			@ApiParam(value = "每页大小") @RequestParam(required = true, defaultValue="10") int pageSize){
    	Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");
		
		SnsMsgInboxExample ex = new SnsMsgInboxExample();
		ex.createCriteria()
		  .andStoreIdEqualTo(storeId)
		  .andReceiverIdEqualTo(memberId)
		  .andIsEnableEqualTo(Constant.YES_INT)
		  .andMsgTypeEqualTo(SnsMsg.MSG_TYPE_COMMENT);
		ex.setOrderByClause("is_read ASC, create_time DESC");
		
		PageView<SnsMsgInbox> pageView = new PageView<>(pageNo, pageSize);
		ex.setPageView(pageView);
		
		pageView = this.snsMsgInboxService.queryObjByPage(ex);
		
		PageView<CommentMsgInbox> tmp = new PageView<>(pageNo, pageSize);
		tmp.setRowCount(pageView.getRowCount());
		tmp.setQueryResult(this.snsMsgInboxService.transform(pageView.getQueryResult()));
		return tmp;
    }
    
    @ApiOperation(httpMethod="GET", value="查询系统消息")
    @RequestMapping("front/findSystemMsg")
    @ResponseBody
    public PageView<SnsMsgInbox> findSystemMsg(HttpSession session,
			@ApiParam(value = "页码") @RequestParam(required = true, defaultValue="1") int pageNo,
			@ApiParam(value = "每页大小") @RequestParam(required = true, defaultValue="10") int pageSize){
    	Integer storeId = (Integer) session.getAttribute("storeId");
		Integer memberId = (Integer) session.getAttribute("memberId");
		
		SnsMsgInboxExample ex = new SnsMsgInboxExample();
		ex.createCriteria()
		  .andStoreIdEqualTo(storeId)
		  .andReceiverIdEqualTo(memberId)
		  .andIsEnableEqualTo(Constant.YES_INT)
		  .andMsgTypeEqualTo(SnsMsg.MSG_TYPE_SYSTEM);
		ex.setOrderByClause("is_read ASC, create_time DESC");
		
		PageView<SnsMsgInbox> pageView = new PageView<>(pageNo, pageSize);
		ex.setPageView(pageView);
		
		return this.snsMsgInboxService.queryObjByPage(ex);
    }
    
    @ApiOperation(httpMethod="GET", value="设置消息为已读")
    @RequestMapping("front/setMsgIsReaded")
    @ResponseBody
    public JsonResult<String> setMsgIsReaded(@ApiParam(value="收信箱的消息id") final int msgInboxId){
    	Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				SnsMsgInbox msgInbox = new SnsMsgInbox();
				msgInbox.setId(msgInboxId);
				msgInbox.setIsRead(Constant.YES_INT);
				SnsMsgInboxController.this.snsMsgInboxService.modifyObj(msgInbox);
			}
		});
    	thread.start();
    	
    	JsonResult<String> result = new JsonResult<>();
		result.setMsg("ok");
		result.setSize(0);
		result.setStatus("00");
		return result;
    }
}