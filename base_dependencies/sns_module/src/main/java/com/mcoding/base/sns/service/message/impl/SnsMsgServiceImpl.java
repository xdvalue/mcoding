package com.mcoding.base.sns.service.message.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.core.PageView;
import com.mcoding.base.sns.bean.message.SnsMsg;
import com.mcoding.base.sns.bean.message.SnsMsgExample;
import com.mcoding.base.sns.persistence.message.SnsMsgMapper;
import com.mcoding.base.sns.service.message.SnsMsgCommand;
import com.mcoding.base.sns.service.message.SnsMsgInboxService;
import com.mcoding.base.sns.service.message.SnsMsgService;

@Service("snsMsgService")
public class SnsMsgServiceImpl implements SnsMsgService {
	
	private Logger logger = LoggerFactory.getLogger(SnsMsgServiceImpl.class);
	
    @Resource
    protected SnsMsgMapper snsMsgMapper;
    
    @Resource
    protected SnsMsgInboxService snsMsgInboxService;

    @CacheEvict(value={"snsMsg"}, allEntries=true)
    @Override
    public void addObj(SnsMsg t) {
        this.snsMsgMapper.insertSelective(t);
    }

    @CacheEvict(value={"snsMsg"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.snsMsgMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"snsMsg"}, allEntries=true)
    @Override
    public void modifyObj(SnsMsg t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.snsMsgMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="snsMsg", key="'SnsMsgService_' + #root.methodName + '_' +#id")
    @Override
    public SnsMsg queryObjById(int id) {
        return this.snsMsgMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="snsMsg", key="'SnsMsgService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<SnsMsg> queryAllObjByExample(SnsMsgExample example) {
        return this.snsMsgMapper.selectByExample(example);
    }

    @Cacheable(value="snsMsg", key="'SnsMsgService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<SnsMsg> queryObjByPage(SnsMsgExample example) {
        PageView<SnsMsg> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.snsMsgMapper.selectByExampleByPage(example));
        return pageView;
    }

    /*@CacheEvict(value={"snsMsg"}, allEntries=true)
    @Transactional
	@Override
	public void addCommentMsg(SnsMsg snsMsg) {
		if (!SnsMsg.MSG_TYPE_COMMENT.equals(snsMsg.getType())) {
			throw new CommonException("提交的不是一个评论，无法发出评论消息");
		}
		
		this.addObj(snsMsg);
		
		SnsMsgInbox snsMsgInbox = new SnsMsgInbox();
		snsMsgInbox.setStoreId(snsMsg.getStoreId());
		snsMsgInbox.setMsgId(snsMsg.getId());
		snsMsgInbox.setMsgType(SnsMsg.MSG_TYPE_COMMENT);
		snsMsgInbox.setReceiverId(snsMsg.getReceiverId());
		snsMsgInbox.setIsRead(Constant.NO_INT);
		this.snsMsgInboxService.addObj(snsMsgInbox);
	}*/

    @CacheEvict(value={"snsMsg"}, allEntries=true, condition="#msgCommand.beforeSend()")
    @Transactional
	@Override
	public boolean sendMsg(SnsMsgCommand msgCommand) throws Exception {
		
		if (!msgCommand.beforeSend()) {
			logger.warn("before send msg , it failed");
			return false;
		}
		msgCommand.execute();
		msgCommand.afterComplete();
		
		return true;
	}
}