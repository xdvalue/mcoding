package com.mcoding.comp.wechat.msg.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.PageView;
import com.mcoding.base.ui.utils.common.Constant;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;
import com.mcoding.comp.wechat.account.bean.AccountConfig;
import com.mcoding.comp.wechat.account.utils.WxMpServiceUtils;
import com.mcoding.comp.wechat.common.McodingMessageRouter;
import com.mcoding.comp.wechat.msg.WxMpMessageRouterFactory;
import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.bean.WxMsgRuleExample;
import com.mcoding.comp.wechat.msg.persistence.WxMsgRuleMapper;
import com.mcoding.comp.wechat.msg.service.WxMsgRuleService;

@Service("wxMsgRuleService")
public class WxMsgRuleServiceImpl implements WxMsgRuleService {
    @Resource
    protected WxMsgRuleMapper wxMsgRuleMapper;

    @CacheEvict(value={"wxMsgRule"}, allEntries=true)
    @Override
    public void addObj(WxMsgRule t) {
        this.wxMsgRuleMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxMsgRule"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxMsgRuleMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxMsgRule"}, allEntries=true)
    @Override
    public void modifyObj(WxMsgRule t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxMsgRuleMapper.updateByPrimaryKeyWithBLOBs(t);
//        this.wxMsgRuleMapper.updateByPrimaryKeyWithBLOBs(record)
    }

    @Cacheable(value="wxMsgRule", key="'WxMsgRuleService_' + #root.methodName + '_' +#id")
    @Override
    public WxMsgRule queryObjById(int id) {
        return this.wxMsgRuleMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxMsgRule", key="'WxMsgRuleService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxMsgRule> queryAllObjByExample(WxMsgRuleExample example) {
        return this.wxMsgRuleMapper.selectByExampleWithBLOBs(example);
    }

    @Cacheable(value="wxMsgRule", key="'WxMsgRuleService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxMsgRule> queryObjByPage(WxMsgRuleExample example) {
        PageView<WxMsgRule> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxMsgRuleMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public McodingMessageRouter createRouter(AccountConfig account) {
    	WxMsgRuleExample example = new WxMsgRuleExample();
    	example.createCriteria()
    	       .andWxAccountIdEqualTo(account.getId())
    	       .andIsEnableEqualTo(Constant.YES_INT);
    	
    	example.setOrderByClause("priority DESC");
    	
    	WxMsgRuleService wxMsgRuleService = SpringContextHolder.getOneBean(WxMsgRuleService.class);
    	List<WxMsgRule> list = wxMsgRuleService.queryAllObjByExample(example);
    	
    	McodingMessageRouter router = null;
		try {
			router = WxMpMessageRouterFactory.build(list, WxMpServiceUtils.getWxMpServiceByAccount(account));
		
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new CommonException("创建消息处理器失败");
		}
		return router;
	}
}