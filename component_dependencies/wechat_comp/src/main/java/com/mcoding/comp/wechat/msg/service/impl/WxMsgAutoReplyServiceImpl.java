package com.mcoding.comp.wechat.msg.service.impl;

import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReply;
import com.mcoding.comp.wechat.msg.bean.WxMsgAutoReplyExample;
import com.mcoding.comp.wechat.msg.persistence.WxMsgAutoReplyMapper;
import com.mcoding.comp.wechat.msg.service.WxMsgAutoReplyService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("wxMsgAutoReplyService")
public class WxMsgAutoReplyServiceImpl implements WxMsgAutoReplyService {
    @Resource
    protected WxMsgAutoReplyMapper wxMsgAutoReplyMapper;

    @CacheEvict(value={"wxMsgAutoReply"}, allEntries=true)
    @Override
    public void addObj(WxMsgAutoReply t) {
        this.wxMsgAutoReplyMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxMsgAutoReply"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxMsgAutoReplyMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxMsgAutoReply"}, allEntries=true)
    @Override
    public void modifyObj(WxMsgAutoReply t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxMsgAutoReplyMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxMsgAutoReply", key="'WxMsgAutoReplyService_' + #root.methodName + '_' +#id")
    @Override
    public WxMsgAutoReply queryObjById(int id) {
        return this.wxMsgAutoReplyMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxMsgAutoReply", key="'WxMsgAutoReplyService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxMsgAutoReply> queryAllObjByExample(WxMsgAutoReplyExample example) {
        return this.wxMsgAutoReplyMapper.selectByExample(example);
    }

    @Cacheable(value="wxMsgAutoReply", key="'WxMsgAutoReplyService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxMsgAutoReply> queryObjByPage(WxMsgAutoReplyExample example) {
        PageView<WxMsgAutoReply> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxMsgAutoReplyMapper.selectByExampleByPage(example));
        return pageView;
    }

    @CacheEvict(value={"wxMsgAutoReply"}, allEntries=true)
    @Transactional
	@Override
	public void setDefalutById(String originId, int id) {
		WxMsgAutoReplyExample example = new WxMsgAutoReplyExample();
		example.createCriteria().andWxAccountOriginIdEqualTo(originId);
		
		WxMsgAutoReply tmp = new WxMsgAutoReply();
		tmp.setIsDefault(Constant.NO_INT);
		this.wxMsgAutoReplyMapper.updateByExampleSelective(tmp, example);
		
		tmp.setId(id);
		tmp.setIsDefault(Constant.YES_INT);
		this.wxMsgAutoReplyMapper.updateByPrimaryKeySelective(tmp);
	}
}