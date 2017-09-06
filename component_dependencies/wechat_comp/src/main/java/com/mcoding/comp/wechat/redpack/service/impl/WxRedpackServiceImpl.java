package com.mcoding.comp.wechat.redpack.service.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.comp.wechat.redpack.bean.WxRedpack;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackExample;
import com.mcoding.comp.wechat.redpack.persistence.WxRedpackMapper;
import com.mcoding.comp.wechat.redpack.service.WxRedpackService;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("wxRedpackService")
public class WxRedpackServiceImpl implements WxRedpackService {
    @Resource
    protected WxRedpackMapper wxRedpackMapper;

    @CacheEvict(value={"wxRedpack"}, allEntries=true)
    @Override
    public void addObj(WxRedpack t) {
        this.wxRedpackMapper.insertSelective(t);
    }

    @CacheEvict(value={"wxRedpack"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.wxRedpackMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"wxRedpack"}, allEntries=true)
    @Override
    public void modifyObj(WxRedpack t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.wxRedpackMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="wxRedpack", key="'WxRedpackService_' + #root.methodName + '_' +#id")
    @Override
    public WxRedpack queryObjById(int id) {
        return this.wxRedpackMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="wxRedpack", key="'WxRedpackService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<WxRedpack> queryAllObjByExample(WxRedpackExample example) {
        return this.wxRedpackMapper.selectByExample(example);
    }

    @Cacheable(value="wxRedpack", key="'WxRedpackService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<WxRedpack> queryObjByPage(WxRedpackExample example) {
        PageView<WxRedpack> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.wxRedpackMapper.selectByExampleByPage(example));
        return pageView;
    }

	@Override
	public WxRedpack queryByCode(String redpackCode) {
		WxRedpackExample example = new WxRedpackExample();
		example.createCriteria().andRedpackCodeEqualTo(redpackCode);
		
		List<WxRedpack> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		
		return list.get(0);
	}
}