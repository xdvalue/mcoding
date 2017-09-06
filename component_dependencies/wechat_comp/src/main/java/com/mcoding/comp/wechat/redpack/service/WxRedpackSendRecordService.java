package com.mcoding.comp.wechat.redpack.service;

import java.net.UnknownHostException;
import java.util.Map;

import com.mcoding.base.core.BaseService;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;

import me.chanjar.weixin.common.exception.WxErrorException;

public interface WxRedpackSendRecordService extends BaseService<WxRedpackSendRecord, WxRedpackSendRecordExample> {

	/**
	 * 插入
	 * @param redpack
	 * @param billNo
	 * @param reopenid
	 * @param amountTotal
	 * @param accountConfig
	 */
//	public void addRedpackSendRecord(WxRedpack redpack, String billNo, String reopenid, Integer amountTotal, AccountConfig accountConfig);
	
	/**
	 * 同步红包的状态
	 * @param id
	 * @throws WxErrorException 
	 */
	public String syncStatus(WxRedpackSendRecord wxRedpackSendRecord) throws WxErrorException;
	
	/**
	 * 根据billNo查询红包发送记录
	 * @param billNo
	 * @return
	 */
	public WxRedpackSendRecord queryByBillNo(String billNo);
	
	/**
	 * 统计各个状态的红包状态
	 * @param example
	 * @return
	 */
	public Map<String, Integer> countStatus(WxRedpackSendRecordExample example);
	
	/**
	 * 发送红包
	 * @param recordId
	 * @throws WxErrorException 
	 * @throws UnknownHostException 
	 */
	public void sendRedpack(int recordId) throws UnknownHostException, WxErrorException;
	
	/**
	 * 发送红包
	 * @param wxRedpackSendRecord
	 * @throws UnknownHostException 
	 * @throws WxErrorException 
	 */
	public void sendRedpack(WxRedpackSendRecord wxRedpackSendRecord) throws UnknownHostException, WxErrorException;
	
}