package com.mcoding.comp.wechat.msg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.msg.bean.WxTemplateMessage;
import com.mcoding.comp.wechat.msg.bean.WxTemplateMessageExample;
import com.mcoding.comp.wechat.msg.persistence.WxTemplateMessageMapper;
import com.mcoding.comp.wechat.msg.service.WxTemplateMessageService;

@Service("wxTemplateMessageService")
public class WxTemplateMessageServiceImpl implements WxTemplateMessageService {
    @Resource
    protected WxTemplateMessageMapper wxTemplateMessageMapper;

    @CacheEvict(value={"wxTemplateMessage"}, allEntries=true)
    @Override
    public void addObj(WxTemplateMessage t) {
        this.wxTemplateMessageMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxTemplateMessage"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxTemplateMessageMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxTemplateMessage"}, allEntries=true)
    @Override
    public void modifyObj(WxTemplateMessage t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxTemplateMessageMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxTemplateMessage", key="'WxTemplateMessageService_' + #root.methodName + '_' +#id")
    @Override
    public WxTemplateMessage queryObjById(int id) {
        return this.wxTemplateMessageMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxTemplateMessage", key="'WxTemplateMessageService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxTemplateMessage> queryAllObjByExample(WxTemplateMessageExample example) {
        return this.wxTemplateMessageMapper.selectByExample(example);
    }

    @Cacheable(value="wxTemplateMessage", key="'WxTemplateMessageService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxTemplateMessage> queryObjByPage(WxTemplateMessageExample example) {
        PageView<WxTemplateMessage> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxTemplateMessageMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public WxTemplateMessage queryByAccountAndType(int accountId, String type) {
		WxTemplateMessageExample example = new WxTemplateMessageExample();
		example.createCriteria().andWxAccountIdEqualTo(accountId).andTypeEqualTo(type);
		
		List<WxTemplateMessage> list = this.wxTemplateMessageMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		return list.get(0);
		
		
	}
}