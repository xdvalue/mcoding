package com.mcoding.base.dst.service.income.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.dst.bean.income.DstIncomeProduct;
import com.mcoding.base.dst.bean.income.DstIncomeProductExample;
import com.mcoding.base.dst.persistence.income.DstIncomeProductMapper;
import com.mcoding.base.dst.service.income.DstIncomeProductService;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("defaultDstIncomeProductService")
public class DstIncomeProductServiceImpl implements DstIncomeProductService {
    @Resource
    protected DstIncomeProductMapper dstIncomeProductMapper;

    @CacheEvict(value={"dstIncomeProduct"}, allEntries=true)
    @Override
    public void addObj(DstIncomeProduct t) {
        this.dstIncomeProductMapper.insertSelective(t);
    }

    @CacheEvict(value={"dstIncomeProduct"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.dstIncomeProductMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"dstIncomeProduct"}, allEntries=true)
    @Override
    public void modifyObj(DstIncomeProduct t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.dstIncomeProductMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="dstIncomeProduct", key="'DstIncomeProductService_' + #root.methodName + '_' +#id")
    @Override
    public DstIncomeProduct queryObjById(int id) {
        return this.dstIncomeProductMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="dstIncomeProduct", key="'DstIncomeProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<DstIncomeProduct> queryAllObjByExample(DstIncomeProductExample example) {
        return this.dstIncomeProductMapper.selectByExample(example);
    }

    @Cacheable(value="dstIncomeProduct", key="'DstIncomeProductService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<DstIncomeProduct> queryObjByPage(DstIncomeProductExample example) {
        PageView<DstIncomeProduct> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.dstIncomeProductMapper.selectByExampleByPage(example));
        return pageView;
    }

    @Cacheable(value="dstIncomeProduct", key="'DstIncomeProductService_' + #root.methodName + '_'+ #levelId + '_' + #productId")
	@Override
	public DstIncomeProduct queryByLevelId(int levelId, int productId) {
		DstIncomeProductExample example = new DstIncomeProductExample();
		example.createCriteria().andLevelIdEqualTo(levelId).andProductIdEqualTo(productId);
		
		List<DstIncomeProduct> list = this.queryAllObjByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
}