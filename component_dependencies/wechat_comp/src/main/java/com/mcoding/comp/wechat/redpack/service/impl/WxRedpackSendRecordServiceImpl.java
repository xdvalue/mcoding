package com.mcoding.comp.wechat.redpack.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.redpack.RedpackSender;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;
import com.mcoding.comp.wechat.redpack.persistence.WxRedpackSendRecordMapper;
import com.mcoding.comp.wechat.redpack.service.WxRedpackSendRecordService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.pay.request.WxPaySendRedpackRequest;
import me.chanjar.weixin.mp.bean.pay.result.WxPayRedpackQueryResult;

@Service("wxRedpackSendRecordService")
public class WxRedpackSendRecordServiceImpl implements WxRedpackSendRecordService {
	
	@Value("#{sysConfig['mode.debug']}")
	private Boolean openPennyToBuy;
	
	@Autowired
	private AccountConfigService accountConfigService;
	
    @Resource
    protected WxRedpackSendRecordMapper wxRedpackSendRecordMapper;

    @CacheEvict(value={"wxRedpackSendRecord"}, allEntries=true)
    @Override
    public void addObj(WxRedpackSendRecord t) {
        this.wxRedpackSendRecordMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxRedpackSendRecord"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxRedpackSendRecordMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxRedpackSendRecord"}, allEntries=true)
    @Override
    public void modifyObj(WxRedpackSendRecord t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxRedpackSendRecordMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxRedpackSendRecord", key="'WxRedpackSendRecordService_' + #root.methodName + '_' +#id")
    @Override
    public WxRedpackSendRecord queryObjById(int id) {
        return this.wxRedpackSendRecordMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxRedpackSendRecord", key="'WxRedpackSendRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxRedpackSendRecord> queryAllObjByExample(WxRedpackSendRecordExample example) {
        return this.wxRedpackSendRecordMapper.selectByExample(example);
    }

    @Cacheable(value="wxRedpackSendRecord", key="'WxRedpackSendRecordService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxRedpackSendRecord> queryObjByPage(WxRedpackSendRecordExample example) {
        PageView<WxRedpackSendRecord> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxRedpackSendRecordMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"wxRedpackSendRecord"}, allEntries=true)
	@Override
	public String syncStatus(WxRedpackSendRecord wxRedpackSendRecord) throws WxErrorException {
		AccountConfig accountConfig = this.accountConfigService.queryObjById(wxRedpackSendRecord.getAccountId());
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(accountConfig);
		
		//查询红包的状态
		WxPayRedpackQueryResult result = wxMpService.getPayService().queryRedpack(wxRedpackSendRecord.getBillNo());
		
		//如果状态没变，返回
		if (result.getStatus().equals(wxRedpackSendRecord.getStatus())) {
			return result.getStatus();
		}
		
		//如果状态改变，就更新状态
		Date receiveTime = null;
		if (CollectionUtils.isNotEmpty(result.getHblist()) 
				&& StringUtils.isNotBlank(result.getHblist().get(0).getReceiveTime())) {
			try {
				receiveTime = DateUtils.parseDate(result.getHblist().get(0).getReceiveTime(), "yyyy-MM-dd hh:mm:ss");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		WxRedpackSendRecord tmp = new WxRedpackSendRecord();
		tmp.setId(wxRedpackSendRecord.getId());
		tmp.setStatus(result.getStatus());
		tmp.setReceiveTime(receiveTime);
		this.modifyObj(tmp);
		
		return result.getStatus();
	}

	@Cacheable(value="wxRedpackSendRecord", key="'WxRedpackSendRecordService_' + #root.methodName + '_'+ #billNo")
	@Override
	public WxRedpackSendRecord queryByBillNo(String billNo) {
		WxRedpackSendRecordExample example = new WxRedpackSendRecordExample();
		example.createCriteria().andBillNoEqualTo(billNo);
		
		List<WxRedpackSendRecord> list = this.queryAllObjByExample(example);
 		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@Cacheable(value="wxRedpackSendRecord", key="'WxRedpackSendRecordService_' + #root.methodName + '_'+ #example.toJson()")
	@Override
	public Map<String, Integer> countStatus(WxRedpackSendRecordExample example) {
		List<Map<String, Integer>> list = this.wxRedpackSendRecordMapper.countStatus(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	@CacheEvict(value={"wxRedpackSendRecord"}, allEntries=true)
	@Override
	public void sendRedpack(int recordId) throws UnknownHostException, WxErrorException {
		WxRedpackSendRecord record = this.queryObjById(recordId);
		this.sendRedpack(record);
	}

	@CacheEvict(value={"wxRedpackSendRecord"}, allEntries=true)
	@Override
	public void sendRedpack(WxRedpackSendRecord record) throws UnknownHostException, WxErrorException {
		if (!WxRedpackSendRecord.STATUS_FAILED.equals(record.getStatus())
				&& !WxRedpackSendRecord.STATUS_REFUND.equals(record.getStatus())
				&& !WxRedpackSendRecord.STATUS_UN_SENT.equals(record.getStatus())) {
			throw new CommonException("该红包已经发出，不能重复发");
		}
		
		if (WxRedpackSendRecord.STATUS_REFUND.equals(record.getStatus())
				|| WxRedpackSendRecord.STATUS_FAILED.equals(record.getStatus())) {
			String newBillNo = record.getBillNo().replaceAll("^\\d+S", DateFormatUtils.format(new Date(), "yyyyMMdd") + "S");
			
			WxRedpackSendRecord tmp = new WxRedpackSendRecord();
			tmp.setId(record.getId());
			tmp.setBillNo(newBillNo);
			this.modifyObj(tmp);
			
			record.setBillNo(newBillNo);
		}
		
		AccountConfig accountConfig = this.accountConfigService.queryObjById(record.getAccountId());
		
		WxPaySendRedpackRequest outMessage = new WxPaySendRedpackRequest();
		outMessage.setNonceStr(RandomUtils.getRandomStr());
		outMessage.setMchId(accountConfig.getMchId());
		outMessage.setAppid(accountConfig.getAppId());
		outMessage.setTotalNum(1);

		outMessage.setMchBillNo(record.getBillNo());
		outMessage.setSendName(StringUtils.defaultIfBlank(record.getSendName(), accountConfig.getName()));
		outMessage.setReOpenid(record.getOpenid());
		outMessage.setWishing(record.getWishing());
		outMessage.setActName(record.getActName());
		outMessage.setRemark(record.getRemark());
		outMessage.setSceneId(record.getSceneId());

		if (openPennyToBuy!=null && openPennyToBuy) {
			outMessage.setTotalAmount(100);
		}else{
			outMessage.setTotalAmount(record.getTotalAmount());
		}
		
		outMessage.setClientIp(InetAddress.getLocalHost().getHostAddress());
	    RedpackSender.sendNormalRedpack(outMessage, accountConfig);
	}

	/*@Override
	public void addRedpackSendRecord(WxRedpack redpack, String billNo, String reopenid, Integer amountTotal, AccountConfig accountConfig) {
		WxMember wxMember = this.wxMemberService.queryByOpenId(reopenid);
		WxRedpackSendRecord record = new WxRedpackSendRecord();
		record.setAccountId(accountConfig.getId());
		record.setAccountName(accountConfig.getName());
		record.setActName(redpack.getActName());
		record.setBillNo(billNo);
		record.setMemberName(wxMember.getWxNickname());
		record.setRedpackCode(redpack.getRedpackCode());
		record.setRedpackId(redpack.getId());
		record.setRemark(redpack.getRemark());
		record.setSceneId(redpack.getSceneId());
		record.setSendName(redpack.getSendName());
		record.setTotalAmount(amountTotal);
		record.setWishing(redpack.getWishing());
	}*/
}