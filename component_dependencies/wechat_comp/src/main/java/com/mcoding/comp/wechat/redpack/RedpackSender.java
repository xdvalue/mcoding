package com.mcoding.comp.wechat.redpack;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.mcoding.base.core.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.common.WxConstant;
import com.mcoding.comp.wechat.redpack.bean.WxRedpack;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackExample;
import com.mcoding.comp.wechat.redpack.service.WxRedpackService;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.pay.request.WxPaySendRedpackRequest;
import me.chanjar.weixin.mp.bean.pay.result.WxPaySendRedpackResult;

/***
 * 红包发送处理
 * 
 * @author hzy
 *
 */
public class RedpackSender {

	private static WxRedpackService wxRedpackService = SpringContextHolder.getOneBean(WxRedpackService.class);

	/**
	 * 发放普通红包
	 * 
	 * @param redpackCode
	 *            红包代码
	 * @param reopenid
	 * @param billNo
	 *            格式如下: yyyymmdd+10位一天内不能重复的数字
	 * @param accountConfig
	 * @return
	 * @throws UnknownHostException
	 * @throws WxErrorException
	 */
	public static void sendNormalRedpack(String redpackCode, String reopenid, String billNo,
			AccountConfig accountConfig)
			throws IllegalArgumentException, IllegalAccessException, UnknownHostException, WxErrorException {
		WxRedpackExample example = new WxRedpackExample();
		example.createCriteria().andRedpackCodeEqualTo(redpackCode).andIsEnableEqualTo(Constant.YES_INT);

		List<WxRedpack> list = RedpackSender.wxRedpackService.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			throw new NullPointerException("没有找到code[" + redpackCode + "] 的红包");
		}

		RedpackSender.sendNormalRedpack(list.get(0), reopenid, billNo, accountConfig);
	}

	/**
	 * 发放普通红包
	 * 
	 * @param redpack
	 * @param reopenid
	 * @param billNo
	 *            格式如下: yyyymmdd+10位一天内不能重复的数字
	 * @param accountConfig
	 * @return
	 * @throws UnknownHostException
	 * @throws WxErrorException
	 */
	public static WxPaySendRedpackResult sendNormalRedpack(final WxRedpack redpack, final String reopenid,
			final String billNo, final AccountConfig accountConfig)
			throws IllegalArgumentException, IllegalAccessException, UnknownHostException, WxErrorException {
		if (StringUtils.isBlank(accountConfig.getCertPath())) {
			throw new IllegalArgumentException("公众号配置中，缺乏支付证书的配置");
		}
		if (StringUtils.isBlank(redpack.getWishing()) || StringUtils.isBlank(redpack.getActName())
				|| StringUtils.isBlank(redpack.getRemark())) {
			throw new NullPointerException("红包配置中，配置不全，请检查祝福语、活动名、或备注");
		}

		if (redpack.getQuotaLimitUp() == null || redpack.getQuotaLimitUp() <= 0) {
			throw new NullPointerException("红包配置中，额度的上限不能为空");
		}

		Integer amountTotal = 0;
		if (Constant.YES_INT.equals(redpack.getIsRandom()) && redpack.getQuotaLimitDown() == null) {
			redpack.setQuotaLimitDown(0);
			amountTotal = (int) (redpack.getQuotaLimitDown()
					+ (redpack.getQuotaLimitUp() - redpack.getQuotaLimitDown()) * Math.random());

		} else {
			amountTotal = redpack.getQuotaLimitUp();
		}

		WxPaySendRedpackRequest outMessage = new WxPaySendRedpackRequest();
		outMessage.setNonceStr(RandomUtils.getRandomStr());
		outMessage.setMchId(accountConfig.getMchId());
		outMessage.setAppid(accountConfig.getAppId());
		outMessage.setTotalNum(1);
		outMessage.setClientIp(InetAddress.getLocalHost().getHostAddress());

		outMessage.setMchBillNo(accountConfig.getMchId() + billNo);
		outMessage.setSendName(StringUtils.defaultIfBlank(redpack.getSendName(), accountConfig.getName()));
		outMessage.setReOpenid(reopenid);
		outMessage.setWishing(redpack.getWishing());
		outMessage.setActName(redpack.getActName());
		outMessage.setRemark(redpack.getRemark());
		outMessage.setSceneId(redpack.getSceneId());

		outMessage.setTotalAmount(amountTotal);
		return sendNormalRedpack(outMessage, accountConfig);
	}
	
	public static WxPaySendRedpackResult sendNormalRedpack(WxPaySendRedpackRequest outMessage, AccountConfig accountConfig) throws WxErrorException{
		WxPaySendRedpackResult result = null;
		WxRedpackSentEvent event = new WxRedpackSentEvent("");
		event.setOutMessage(outMessage);
		event.setAccountConfig(accountConfig);
		
		try {
			WxMpService service = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
			result = service.getPayService().sendRedpack(outMessage);
			event.setInMessage(result);

		} catch (Exception e) {
			e.printStackTrace();
			if (result == null) {
				result = new WxPaySendRedpackResult();
				result.setReturnCode(WxConstant.RETURN_CODE_FAIL);
				result.setResultCode(WxConstant.RETURN_CODE_FAIL);
				result.setErrCode(e.getMessage());
			}
			event.setInMessage(result);
		}

		SpringContextHolder.getApplicationContext().publishEvent(event);
		if (!WxConstant.RETURN_CODE_SUCCESS.equals(result.getReturnCode()) || !WxConstant.RETURN_CODE_SUCCESS.equals(result.getResultCode())) {
			WxError error = new WxError();
			error.setErrorCode(-1);
			error.setErrorMsg("code:" + result.getReturnCode() + ",msg:" + result.getReturnMsg());
			throw new WxErrorException(error);
		}

		return result;
	}

}
