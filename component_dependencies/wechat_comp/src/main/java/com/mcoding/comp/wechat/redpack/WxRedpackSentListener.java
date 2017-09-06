package com.mcoding.comp.wechat.redpack;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mcoding.comp.wechat.member.bean.WxMember;
import com.mcoding.comp.wechat.member.service.WxMemberService;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;

/**
 * 红包发送监听器，红包发送后，更新红包的发送状态
 * @author hzy
 *
 */
@Component
public class WxRedpackSentListener implements ApplicationListener<WxRedpackSentEvent>{

	@Autowired
	protected WxRedpackSendRecordService wxRedpackSendRecordService;
	@Autowired
	protected WxMemberService wxMemberService;
	
	@Override
	public void onApplicationEvent(WxRedpackSentEvent event) {
		//设置红包发送状态
		String status = null;
		if ("SUCCESS".equals(event.getInMessage().getReturnCode()) && "SUCCESS".equals(event.getInMessage().getResultCode())) {
			status = WxRedpackSendRecord.STATUS_SENT;
		}else{
			status = WxRedpackSendRecord.STATUS_FAILED;
		}
		//获取红包发送的状态
		String remark = event.getInMessage().getErrCodeDes();
		
		//查询是否有红包发送记录
		String billNo = event.getOutMessage().getMchBillNo();
		WxRedpackSendRecord record = this.wxRedpackSendRecordService.queryByBillNo(billNo);
		
		//如果已经有记录，就更新记录的状态和备注
		if (record != null) {
			WxRedpackSendRecord tmp = new WxRedpackSendRecord();
			tmp.setId(record.getId());
			tmp.setRemark(remark);
			tmp.setSendTime(new Date());
			tmp.setStatus(status);
			this.wxRedpackSendRecordService.modifyObj(tmp);
			
			return;
		}
		
		//如果没有有记录，就添加记录
		WxMember wxMember = this.wxMemberService.queryByOpenId(event.getOutMessage().getReOpenid());
		
		record = new WxRedpackSendRecord();
		record.setAccountId(event.getAccountConfig().getId());
		record.setAccountName(event.getAccountConfig().getName());
		record.setBillNo(event.getOutMessage().getMchBillNo());
		record.setOpenid(event.getOutMessage().getReOpenid());
		record.setRedpackId(event.getWxRedpack().getId());
		record.setRedpackCode(event.getWxRedpack().getRedpackCode());
		record.setTotalAmount(event.getOutMessage().getTotalAmount());
		record.setSceneId(event.getOutMessage().getSceneId());
		record.setActName(event.getOutMessage().getActName());
		record.setSendName(event.getOutMessage().getSendName());
		record.setWishing(event.getOutMessage().getWishing());
		record.setSendTime(new Date());
		if(wxMember != null) record.setMemberName(wxMember.getWxNickname());
		record.setStatus(status);
		record.setRemark(remark);
		
		this.wxRedpackSendRecordService.addObj(record);
	}

}
