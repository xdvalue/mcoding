package com.mcoding.base.wechat.web.controller.msgrule;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.bean.WxMsgRuleExample;
import com.mcoding.comp.wechat.msg.bean.WxMsgRuleExample.Criteria;
import com.mcoding.comp.wechat.msg.handler.ReplyNewsHandler;
import com.mcoding.comp.wechat.msg.handler.ReplyTextHandler;
import com.mcoding.comp.wechat.msg.service.WxMsgRuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.api.WxConsts;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="微信消息规则")
@Controller
@RequestMapping("wxMsgRule")
public class WxMsgRuleController {
    @Resource
    protected WxMsgRuleService wxMsgRuleService;
    
    @Resource
    protected AccountConfigService accountConfigService;
    
    /*@ApiIgnore
    @RequestMapping("service/toAddAutoReplyView")
    public ModelAndView toAddAutoReplyView(@RequestParam(required=true, value="originId") String originId, int id){
    	ModelAndView view = new ModelAndView("wechat/wxMsgRule/autoReply/toAddView");
    	
    	view.addObject("wxMsgRule", this.wxMsgRuleService.queryObjById(id));
    	view.addObject("account", accountConfigService.queryByOriginId(originId));
    	
    	return view;
    }

    @ApiIgnore
    @RequestMapping("service/toAutoReplyView")
    public ModelAndView toAutoReplyView(@RequestParam(required=true, value="originId") String originId){
    	ModelAndView view = new ModelAndView("wechat/wxMsgRule/autoReply/toMainView");
    	view.addObject("account", accountConfigService.queryByOriginId(originId));
        return view;
    }

    @ApiOperation("查询自动回复的规则")
    @RequestMapping("service/findAutoReplys")
    @ResponseBody
    public PageView<WxMsgRule> findAutoReplys(
    		@ApiParam(name="分页索引",defaultValue="0") @RequestParam(defaultValue="0") String iDisplayStart, 
    		@ApiParam(name="每页的数量",defaultValue="10") @RequestParam(defaultValue="10") String iDisplayLength, 
    		@ApiParam(name="查询条件") String sSearch,
    		@ApiParam(name="公众号原始id") String originId){
    	WxMsgRuleExample wxMsgRuleExample = new WxMsgRuleExample();
    	WxMsgRuleExample.Criteria cri = wxMsgRuleExample.createCriteria();
    	
    	cri.andHandlersEqualTo(ReplyTextHandler.class.toString());
    	cri.andWxAccountOriginIdEqualTo(originId);
    	
    	WxMsgRuleExample.Criteria orCri = wxMsgRuleExample.or();
    	orCri.andHandlersEqualTo(ReplyNewsHandler.class.toString());
    	orCri.andWxAccountOriginIdEqualTo(originId);
    	
    	wxMsgRuleExample.setOrderByClause("priority DESC");
    	return this.wxMsgRuleService.queryObjByPage(wxMsgRuleExample);
    }*/
    
    
    @ApiIgnore
    @RequestMapping("service/toMainView")
    public ModelAndView toMainView(@RequestParam(required=true, value="originId") String originId) {
    	ModelAndView view = new ModelAndView("wechat/wxMsgRule/toMainView");
    	view.addObject("account", accountConfigService.queryByOriginId(originId));
        return view;
    }
    
    /**
     * 
     * @param step 步骤有三种，下一步1、上一步0，取消-1
     * @param originId
     * @param isStart
     * @param request
     * @return
     */
    @RequestMapping("service/toAddOEditView/{step}")
    public ModelAndView toAddOrEdit(@PathVariable("step") int step, Integer isStart, HttpServletRequest request,
    		@RequestParam(required=true, value="originId") String originId){
    	ModelAndView modelAndView = new ModelAndView();
    	
    	Stack<Map<String, Object>> stack = (Stack<Map<String, Object>>) request.getSession().getAttribute("step_data");;
    	Integer stepNum = null;
    	
    	if (stack == null || Constant.YES_INT.equals(isStart)) {
    		stack = new Stack<Map<String, Object>>();
    		stepNum = 0;
		}
    	
    	if(step < 0){
    		//取消
    		stack.removeAllElements();
    		request.getSession().removeAttribute("step_data");
    		stepNum = -1;
    		
    	}else if (step == 0) {
    		//如果是上一步
    		Map<String, Object> preStepData = stack.pop();
    		modelAndView.addObject("wxMsgRule", preStepData.get("wxMsgRule"));
			
    		stepNum = (Integer) stack.peek().get("nextStepNum");
		}else {
			//如果是下一步
			Map<String, Object> stepData = handleNext(stack, request, modelAndView);
			stack.push(stepData);
			
			modelAndView.addObject("wxMsgRule", stepData.get("wxMsgRule"));
			stepNum = (Integer) stepData.get("nextStepNum");
		}
    	modelAndView.setViewName(this.getPageByStep(stepNum));
    	
    	request.getSession().setAttribute("step_data", stack);
//		modelAndView.addObject("account", this.accountConfigService.queryObjById(accountId));
		modelAndView.addObject("account", this.accountConfigService.queryByOriginId(originId));
		
    	return modelAndView;
    }
    
    /**
     * 下一步的操作
     * @param step 要执行的步骤
     * @param preWxMsgRule 上一次数据
     * @param request 请求
     * @param modelAndView 跳转的页面
     * @return
     */
    private Map<String, Object> handleNext(Stack<Map<String, Object>> stack, HttpServletRequest request, ModelAndView modelAndView) {
    	Map<String, Object>stepData = new HashMap<>();
    	
    	if (stack.isEmpty()) {
    		//第一步
			String id = request.getParameter("id");
			if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) {
				stepData.put("wxMsgRule", new WxMsgRule());
				
			}else {
				stepData.put("wxMsgRule", this.wxMsgRuleService.queryObjById(Integer.valueOf(id)));
			}
			stepData.put("nextStepNum", 0);
			
			return stepData;
		}
    	
    	Map<String, Object> preStepData = stack.peek();
    	WxMsgRule preWxMsgRule = (WxMsgRule) preStepData.get("wxMsgRule");
    	WxMsgRule currentWxMsgRule = (WxMsgRule) ObjectUtils.clone(preWxMsgRule);
    	
    	Integer currentStepNum = (Integer) preStepData.get("nextStepNum");
    	Integer nextStep = -1;
    	switch (currentStepNum) {
    	
		case 0:
			String name = request.getParameter("name");
			if (StringUtils.isBlank(name)) {
				throw new CommonException("名称不能为空");
				
			}else {
				currentWxMsgRule.setName(name);
				nextStep = 1;
			}
			break;
			
		case 1:
			String msgType = request.getParameter("msgType");
			if (!ObjectUtils.equals(msgType, currentWxMsgRule.getMsgType())) {
				currentWxMsgRule = new WxMsgRule();
				currentWxMsgRule.setId(preWxMsgRule.getId());
				currentWxMsgRule.setName(preWxMsgRule.getName());
				currentWxMsgRule.setMsgType(msgType);
			}
			
			if (WxConsts.XML_MSG_EVENT.equals(msgType)) {
				currentWxMsgRule.setContent("");
				nextStep = 2;
				
			}else if(WxConsts.XML_MSG_TEXT.equals(msgType)){
				currentWxMsgRule.setEvent("");
				currentWxMsgRule.setEventKey("");
				nextStep = 4;
				
			}else{
				nextStep = 5;
			}
			break;
			
		case 2:
			String event = request.getParameter("event");
            if (WxConsts.EVT_CLICK.equals(event) 
            		|| WxConsts.EVT_SCAN.equals(event) 
            		|| WxConsts.EVT_SUBSCRIBE.equals(event)) {
				nextStep = 3;
			}else{
				
				nextStep = 5;
			}
            currentWxMsgRule.setEvent(event);
			break;
		case 3:
			String eventKey = request.getParameter("eventKey");
			String matchTypeForEventKey = request.getParameter("matchType");
			
			nextStep = 5;
			if (StringUtils.isBlank(eventKey) || eventKey.matches("^\\s*\\[.*\\]\\s*$")) {
				break;
			}
			
			if(!WxMsgRule.MATCH_TYPE_ALL.equals(matchTypeForEventKey) && !WxMsgRule.MATCH_TYPE_REGEX.equals(matchTypeForEventKey)){
				currentWxMsgRule.setMatchType(WxMsgRule.MATCH_TYPE_ALL);
			}
			
			currentWxMsgRule.setEventKey(eventKey);
			currentWxMsgRule.setMatchType(Integer.valueOf(matchTypeForEventKey));
			
			break;
		case 4:
			String content = request.getParameter("content");
			String matchTypeForContent = request.getParameter("matchType");
			
			nextStep = 5;
			if (StringUtils.isBlank(content)) {
				break;
			}
			
			if(!WxMsgRule.MATCH_TYPE_ALL.equals(matchTypeForContent) && !WxMsgRule.MATCH_TYPE_REGEX.equals(matchTypeForContent)){
				currentWxMsgRule.setMatchType(WxMsgRule.MATCH_TYPE_ALL);
			}
			
			currentWxMsgRule.setContent(content);
			currentWxMsgRule.setMatchType(Integer.valueOf(matchTypeForContent));
			break;
			
			
		case 5:
			String handlers = request.getParameter("handlers");
			if (!ObjectUtils.equals(handlers, currentWxMsgRule.getHandlers())) {
				currentWxMsgRule.setReplyContent(null);
			}
			if (StringUtils.isBlank(handlers)) {
				currentWxMsgRule.setHandlers(ReplyTextHandler.class.getName());
			}else {
				currentWxMsgRule.setHandlers(handlers);
			}
			
			if (ReplyTextHandler.class.getName().equals(handlers)) {
				nextStep = 6;
			}else if (ReplyNewsHandler.class.getName().equals(handlers)) {
				nextStep = 9;
			}else {
				nextStep = 6;
			}
			
			break;
		case 6:
			String replytText = request.getParameter("replyContent");
			currentWxMsgRule.setReplyContent(replytText);
			nextStep = 10;
			break;
		case 9:
			String replyNews = request.getParameter("replyContent");
			currentWxMsgRule.setReplyContent(replyNews);
			nextStep = 10;
			break;
		case 10:
			String priority = request.getParameter("priority");
			
			if (StringUtils.isBlank(priority) || !StringUtils.isNumeric(priority)) {
				priority = "0";
			}
			currentWxMsgRule.setPriority(Integer.valueOf(priority));
			nextStep = 7;
			break;
		case 7:
			String startTimeStr = request.getParameter("msgStartTime");
			String endTimeStr = request.getParameter("msgEndTime");
			
			try {
				if (StringUtils.isNotBlank(startTimeStr)
						&& startTimeStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
					currentWxMsgRule.setMsgStartTime(DateUtils.parseDate(startTimeStr, new String[] { "yyyy-MM-dd HH:mm:ss" }));
				}else{
					currentWxMsgRule.setMsgStartTime(null);
				}
				if (StringUtils.isNotBlank(endTimeStr)
						&& endTimeStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
					currentWxMsgRule.setMsgEndTime(DateUtils.parseDate(endTimeStr, new String[] { "yyyy-MM-dd HH:mm:ss" }));
				}else{
					currentWxMsgRule.setMsgEndTime(null);
				}
				
				if (currentWxMsgRule.getId() != null) {
					this.wxMsgRuleService.modifyObj(currentWxMsgRule);
					
				}else {
					AccountConfig account = this.accountConfigService.queryByOriginId(request.getParameter("originId"));
					currentWxMsgRule.setWxAccountOriginId(account.getOriginId());
					currentWxMsgRule.setWxAccountId(account.getId());
					this.wxMsgRuleService.addObj(currentWxMsgRule);
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			nextStep = -1;
			break;
		}
		
    	stepData.put("wxMsgRule", currentWxMsgRule);
		stepData.put("nextStepNum", nextStep);
		
    	return stepData;
	}
	private String getPageByStep(int step){
    	switch (step) {
		case 0:
			//第一步，填写规则名称
			return "wechat/wxMsgRule/addOrEdit/name";
		case 1:
			//第二步，填写匹配的消息类型
			return "wechat/wxMsgRule/addOrEdit/msgType";
		case 2:
			//第三步，填写匹配的事件
			return "wechat/wxMsgRule/addOrEdit/event";
		case 3:
			//第四步，填写匹配的事件key
			return "wechat/wxMsgRule/addOrEdit/eventKey";
		case 4:
			//第五步，填写匹配的消息内容
			return "wechat/wxMsgRule/addOrEdit/content";
		case 5:
			//第六步,填写回复的消息类型，也就是消息的处理器
			return "wechat/wxMsgRule/addOrEdit/handlers";
		case 6:
			//第七步，填写回复的文本消息
			return "wechat/wxMsgRule/addOrEdit/replyText";
		case 9:
			//第八步，填写回复的图文消息
			return "wechat/wxMsgRule/addOrEdit/replyNews";
		case 10:
			//第九步，填写回复的图文消息
			return "wechat/wxMsgRule/addOrEdit/priority";
		case 7:
			//第十步，有效时间
			return "wechat/wxMsgRule/addOrEdit/validTime";
		case 8:
			//完成
			return "wechat/wxMsgRule/addOrEdit/finish";

		default:
			//取消
			return "wechat/wxMsgRule/toMainView";
		}
    	
    }
}