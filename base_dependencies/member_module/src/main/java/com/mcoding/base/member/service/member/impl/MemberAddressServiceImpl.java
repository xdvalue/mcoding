package com.mcoding.base.member.service.member.impl;

import com.mcoding.base.core.CommonException;
import com.mcoding.base.core.Constant;
import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.member.MemberAddress;
import com.mcoding.base.member.bean.member.MemberAddressExample;
import com.mcoding.base.member.persistence.member.MemberAddressMapper;
import com.mcoding.base.member.service.member.MemberAddressService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("memberAddressService")
public class MemberAddressServiceImpl implements MemberAddressService {
    @Resource
    protected MemberAddressMapper memberAddressMapper;
    
    /**
     * 把会员下所有地址设置为非默认
     * @param memberId
     */
    private void resetAllAddressByMember(int memberId){
    	MemberAddressExample example = new MemberAddressExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		
		MemberAddress tmp = new MemberAddress();
		tmp.setIsDefault(Constant.NO_INT);
		this.memberAddressMapper.updateByExampleSelective(tmp, example);
    }

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void addObj(MemberAddress t) {
    	if (t.getMemberId() == null) {
			throw new NullPointerException("会员id不能为空");
		}
    	MemberAddressExample example = new MemberAddressExample();
    	example.createCriteria()
    	       .andMemberIdEqualTo(t.getMemberId());
    	
    	int num = this.memberAddressMapper.countByExample(example);
    	if (num == 0) {
			t.setIsDefault(Constant.YES_INT);
			
		}else if(Constant.YES_INT.equals(t.getIsDefault())){
			this.resetAllAddressByMember(t.getMemberId());
		}
    	
        this.memberAddressMapper.insertSelective(t);
    }

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.memberAddressMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"memberAddress"}, allEntries=true)
    @Override
    public void modifyObj(MemberAddress t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        if (Constant.YES_INT.equals(t.getIsDefault())) {
			
        	MemberAddress memberAddress = this.memberAddressMapper.selectByPrimaryKey(t.getId());
        	if (!Constant.YES_INT.equals(memberAddress.getIsDefault())) {
				this.resetAllAddressByMember(memberAddress.getMemberId());
			}
		}
        
        this.memberAddressMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="memberAddress", key="'MemberAddressService_' + #root.methodName + '_' +#id")
    @Override
    public MemberAddress queryObjById(int id) {
        return this.memberAddressMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="memberAddress", key="'MemberAddressService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<MemberAddress> queryAllObjByExample(MemberAddressExample example) {
        return this.memberAddressMapper.selectByExample(example);
    }

    @Cacheable(value="memberAddress", key="'MemberAddressService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<MemberAddress> queryObjByPage(MemberAddressExample example) {
        PageView<MemberAddress> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.memberAddressMapper.selectByExampleByPage(example));
        return pageView;
    }
}