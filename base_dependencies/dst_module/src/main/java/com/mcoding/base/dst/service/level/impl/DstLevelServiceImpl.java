package com.mcoding.base.dst.service.level.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.level.DstLevel;
import com.mcoding.base.dst.bean.level.DstLevelExample;
import com.mcoding.base.dst.persistence.level.DstLevelMapper;
import com.mcoding.base.dst.service.level.DstLevelService;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("defaultDstLevelService")
public class DstLevelServiceImpl implements DstLevelService {
    @Resource
    protected DstLevelMapper dstLevelMapper;

    @CacheEvict(value={"dstLevel"}, allEntries=true)
    @Override
    public void addObj(DstLevel t) {
        this.dstLevelMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstLevel"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstLevelMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstLevel"}, allEntries=true)
    @Override
    public void modifyObj(DstLevel t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstLevelMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstLevel", key="'DstLevelService_' + #root.methodName + '_' +#id")
    @Override
    public DstLevel queryObjById(int id) {
        return this.dstLevelMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstLevel", key="'DstLevelService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstLevel> queryAllObjByExample(DstLevelExample example) {
        return this.dstLevelMapper.selectByExample(example);
    }

    @Cacheable(value="dstLevel", key="'DstLevelService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstLevel> queryObjByPage(DstLevelExample example) {
        PageView<DstLevel> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstLevelMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="dstLevel", key="'DstLevelService_' + #root.methodName + '_' +#levelId")
	@Override
	public DstLevel queryParentLevelById(int levelId) {
    	DstLevel level = this.queryObjById(levelId);
    	if (level == null) {
    		throw new NullPointerException("该级别不存在");
    	}
    	Integer parentId = level.getParentLevelId();
    	
    	if (parentId == null || parentId <=0) {
			return null;
		}
    	return this.queryObjById(parentId);
	}

    @Cacheable(value="dstLevel", key="'DstLevelService_' + #root.methodName + '_' +#levelId")
	@Override
	public DstLevel queryChildLevelById(int levelId) {
    	DstLevel level = this.queryObjById(levelId);
    	if (level == null) {
			throw new NullPointerException("该级别不存在");
		}
    	
    	DstLevelExample example = new DstLevelExample();
    	example.createCriteria().andParentLevelIdEqualTo(levelId);
    	List<DstLevel> list = this.queryAllObjByExample(example);
    	
    	if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
}