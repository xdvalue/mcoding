package com.mcoding.comp.wechat.qrcode.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.bean.attachment.Attachment;
import com.mcoding.base.ui.service.attachment.AttachmentService;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.service.AccountConfigService;
import com.mcoding.comp.wechat.account.utils.WxAccountConfigUtils;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.service.WxMsgRuleService;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcode;
import com.mcoding.comp.wechat.qrcode.bean.WxQrcodeExample;
import com.mcoding.comp.wechat.qrcode.persistence.WxQrcodeMapper;
import com.mcoding.comp.wechat.qrcode.service.WxQrcodeService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

@Service("wxQrcodeService")
public class WxQrcodeServiceImpl implements WxQrcodeService {
	@Autowired
    protected WxQrcodeMapper wxQrcodeMapper;
    
    @Autowired
    protected AccountConfigService accountConfigService;
    
    @Autowired
    protected AttachmentService attachmentService;
    
    @Autowired
    protected WxMsgRuleService wxMsgRuleService;

    @CacheEvict(value={"wxQrcode"}, allEntries=true)
    @Override
    public void addObj(WxQrcode t) {
        this.wxQrcodeMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxQrcode"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxQrcodeMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxQrcode"}, allEntries=true)
    @Override
    public void modifyObj(WxQrcode t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxQrcodeMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxQrcode", key="'WxQrcodeService_' + #root.methodName + '_' +#id")
    @Override
    public WxQrcode queryObjById(int id) {
        return this.wxQrcodeMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxQrcode", key="'WxQrcodeService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxQrcode> queryAllObjByExample(WxQrcodeExample example) {
        return this.wxQrcodeMapper.selectByExample(example);
    }

    @Cacheable(value="wxQrcode", key="'WxQrcodeService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxQrcode> queryObjByPage(WxQrcodeExample example) {
        PageView<WxQrcode> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxQrcodeMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"wxQrcode"}, allEntries=true)
    @Transactional
	@Override
	public WxQrcode createLimitQrcode(WxQrcode wxQrcode) throws WxErrorException, IOException {
		if(new Date().getTime() > wxQrcode.getValidTime().getTime() - WxQrcode.QRCODE_MAX_EXPIRED_SECOND){
			throw new CommonException("有效时间超出最大时长");
		}
		
		int expiredSeconds = (int) (wxQrcode.getValidTime().getTime() - new Date().getTime());
		
		AccountConfig account = WxAccountConfigUtils.getByOrginId(wxQrcode.getAccountOriginId());
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(Integer.valueOf(wxQrcode.getSceneStr()), expiredSeconds);
		File qrcodePicture = wxMpService.getQrcodeService().qrCodePicture(ticket);
		Attachment attachment =  this.attachmentService.saveFile(qrcodePicture);
		
		if (StringUtils.isBlank(wxQrcode.getName())) {
			wxQrcode.setName("qrcode" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		}
		wxQrcode.setType(WxQrcode.QRCODE_TYPE_LIMIT);
		wxQrcode.setImgUrl(attachment.getFileUrl());
		wxQrcode.setTicket(ticket.getTicket());
		wxQrcode.setContent(ticket.getUrl());
		
		this.addObj(wxQrcode);
		return wxQrcode;
	}

    @CacheEvict(value={"wxQrcode"}, allEntries=true)
    @Transactional
	@Override
	public WxQrcode createUnLimitQrcode(WxQrcode wxQrcode) throws WxErrorException, IOException {
		AccountConfig account = WxAccountConfigUtils.getByOrginId(wxQrcode.getAccountOriginId());
		WxMpService wxMpService = WxMpServiceUtils.getWxMpServiceByAccount(account);
		
		WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateLastTicket(wxQrcode.getSceneStr());
		File qrcodePicture = wxMpService.getQrcodeService().qrCodePicture(ticket);
		Attachment attachment =  this.attachmentService.saveFile(qrcodePicture);
		
		if (StringUtils.isBlank(wxQrcode.getName())) {
			wxQrcode.setName("qrcode" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		}
		wxQrcode.setType(WxQrcode.QRCODE_TYPE_UNLIMIT);
		wxQrcode.setImgUrl(attachment.getFileUrl());
//		wxQrcode.setValidTime(DateUtils.addSeconds(new Date(), WxQrcode.QRCODE_MAX_EXPIRED_SECOND - 60));
		wxQrcode.setTicket(ticket.getTicket());
		wxQrcode.setContent(ticket.getUrl());
		
		this.addObj(wxQrcode);
		return wxQrcode;
	}
    
    
    public void createHandleRuleForQrcode(WxQrcode wxQrcode){
    	WxMsgRule wxMsgRule = new WxMsgRule();
    	wxMsgRule.setMsgType(WxConsts.XML_MSG_EVENT);
    	wxMsgRule.setEventKey(wxQrcode.getSceneStr());
    	wxMsgRule.setHandlers(wxQrcode.getReplyType());
    	wxMsgRule.setReplyContent(wxQrcode.getReplyContent());
    	wxMsgRule.setWxAccountId(wxQrcode.getAccountId());
    	wxMsgRule.setWxAccountOriginId(wxQrcode.getAccountOriginId());
    	wxMsgRule.setName("qrcode_auto_created" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
    	
    	this.wxMsgRuleService.addObj(wxMsgRule);
    	
    	WxQrcode record = new WxQrcode();
    	record.setId(wxQrcode.getId());
    	record.setHandlerId(wxMsgRule.getId());
    	this.wxQrcodeMapper.updateByPrimaryKeySelective(record);
    }

    @Cacheable(value="wxQrcode", key="'WxQrcodeService_' + #root.methodName + '_'+ #originId +'_'+ #scene")
    @Override
	public WxQrcode queryByKey(String originId, String scene) {
		WxQrcodeExample example = new WxQrcodeExample();
		example.createCriteria()
		       .andSceneStrEqualTo(scene)
		       .andAccountOriginIdEqualTo(originId);
		
		List<WxQrcode> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

    @CacheEvict(value={"wxQrcode"}, allEntries=true)
	@Override
	public void addScanQrcode(int id) {
    	this.wxQrcodeMapper.addScanCount(id);
	}
}