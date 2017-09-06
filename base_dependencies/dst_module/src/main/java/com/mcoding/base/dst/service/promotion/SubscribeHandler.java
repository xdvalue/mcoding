package com.mcoding.base.dst.service.promotion;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.mcoding.base.dst.service.level.DstMemberLevelService;
import com.mcoding.base.dst.utils.DstUtils;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.member.bean.WxMember;
import com.mcoding.comp.wechat.member.service.WxMemberService;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.handler.BaseMsgHandler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class SubscribeHandler extends BaseMsgHandler {
	
	protected static WxMemberService wxMemberService = SpringContextHolder.getOneBean(WxMemberService.class);
	protected static MemberService memberService = SpringContextHolder.getOneBean(MemberService.class);
	protected static DstMemberLevelService dstMemberLevelService = SpringContextHolder.getOneBean(DstMemberLevelService.class);

	public SubscribeHandler(WxMsgRule wxMsgRule) {
		super(wxMsgRule);
	}

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		
		
		String qrcodeKey = wxMessage.getEventKey();
		if (StringUtils.isBlank(qrcodeKey)) {
			return null;
		}
		
		qrcodeKey = qrcodeKey.replaceAll("qrscene_", "");
		String openId = wxMessage.getFromUser();
		
		Member currentMember = memberService.queryByOpenId(openId);
		
		if (currentMember == null) {
			WxMember wxMember = wxMemberService.queryByOpenId(openId);
			
			if (wxMember == null) {
				WxMpUser wxMpUser = wxMpService.getUserService().userInfo(openId, null);
				wxMember = wxMemberService.createOrEditWxMember(wxMpUser, qrcodeKey, wxMessage.getToUser());
			}
			currentMember = memberService.createOrEditByWxMember(wxMember);
		}
		
		int distributerId = DstUtils.getDistributorIdFromKey(qrcodeKey);
		Member parentMember = memberService.queryObjById(distributerId);
		dstMemberLevelService.recruitNewMember(currentMember, parentMember);

		return null;
	}

}
