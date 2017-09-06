package com.mcoding.comp.wechat.msg.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.msg.bean.WxMsgReplyNews;
import com.mcoding.comp.wechat.msg.bean.WxMsgReplyNewsExample;
import com.mcoding.comp.wechat.msg.persistence.WxMsgReplyNewsMapper;
import com.mcoding.comp.wechat.msg.service.WxMsgReplyNewsService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("wxMsgReplyNewsService")
public class WxMsgReplyNewsServiceImpl implements WxMsgReplyNewsService {
    @Resource
    protected WxMsgReplyNewsMapper wxMsgReplyNewsMapper;

    @CacheEvict(value={"wxMsgReplyNews"}, allEntries=true)
    @Override
    public void addObj(WxMsgReplyNews t) {
        this.wxMsgReplyNewsMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxMsgReplyNews"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxMsgReplyNewsMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxMsgReplyNews"}, allEntries=true)
    @Override
    public void modifyObj(WxMsgReplyNews t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxMsgReplyNewsMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxMsgReplyNews", key="'WxMsgReplyNewsService_' + #root.methodName + '_' +#id")
    @Override
    public WxMsgReplyNews queryObjById(int id) {
        return this.wxMsgReplyNewsMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxMsgReplyNews", key="'WxMsgReplyNewsService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxMsgReplyNews> queryAllObjByExample(WxMsgReplyNewsExample example) {
        return this.wxMsgReplyNewsMapper.selectByExample(example);
    }

    @Cacheable(value="wxMsgReplyNews", key="'WxMsgReplyNewsService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxMsgReplyNews> queryObjByPage(WxMsgReplyNewsExample example) {
        PageView<WxMsgReplyNews> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxMsgReplyNewsMapper.selectByExampleByPage(example));
        return pageView;
    }
}