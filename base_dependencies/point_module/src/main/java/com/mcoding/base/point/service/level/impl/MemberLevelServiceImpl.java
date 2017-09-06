package com.mcoding.base.point.service.level.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.bean.level.MemberLevel;
import com.mcoding.base.point.bean.level.MemberLevelExample;
import com.mcoding.base.point.bean.pointrecord.MemberPointRecord;
import com.mcoding.base.point.persistence.level.MemberLevelMapper;
import com.mcoding.base.point.service.MemberLeveLRule;
import com.mcoding.base.point.service.level.MemberLevelService;
import com.mcoding.base.point.service.pointrecord.MemberPointRecordService;
import com.mcoding.base.ui.utils.spring.SpringContextHolder;

@Service("memberLevelService")
public class MemberLevelServiceImpl implements MemberLevelService {
	
	@Resource
	protected MemberLevelMapper memberLevelMapper;

	@Resource
	protected MemberPointRecordService memberPointRecordService;
	
	@CacheEvict(value = { "memberLevel" }, key = "'MemberLevelService_list_' +#t.getMemberId()")
	@Override
	public void addObj(MemberLevel t) {
		this.memberLevelMapper.insertSelective(t);
	}

	@CacheEvict(value = { "memberLevel" }, key = "'MemberLevelService_list_' +#id")
	@Override
	public void deleteObjById(int id) {
		this.memberLevelMapper.deleteByPrimaryKey(id);
	}

	@CacheEvict(value = { "memberLevel" }, key = "'MemberLevelService_list_' +#t.id")
	@Override
	public void modifyObj(MemberLevel t) {
		if (t.getId() == null || t.getId() == 0) {
			throw new NullPointerException("id 为空，无法更新");
		}
		this.memberLevelMapper.updateByPrimaryKeySelective(t);
	}
	
	@Cacheable(value = "memberLevel", key = "'MemberLevelService_list_' +#memberId")
	@Override
	public Map<Integer, MemberLevel> queryAllByMemberId(int memberId) {
		MemberLevelExample example = new MemberLevelExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		
		List<MemberLevel> list = this.memberLevelMapper.selectByExample(example);
		Map<Integer, MemberLevel> result = new HashMap<>();
		for(int i=0; CollectionUtils.isNotEmpty(list) && i<list.size(); i++){
			MemberLevel level = list.get(i);
			result.put(level.getModuleType(), level);
		}
		return result;
//		return this.memberLevelMapper.selectByExample(example);
	}

	
	@Override
	public void addPoint(MemberPointData pointData){
		
		this.checkAndInitMemberLevel(pointData.getMember().getId(), pointData.getPointType().getModuleType(), pointData.getStoreId());
		
		//2、添加积分的修改记录
		MemberPointRecord record = pointData.toMemberPointRecord();
		this.memberPointRecordService.addObj(record);
		
		MemberLevelService memberLevelService = SpringContextHolder.getBean("memberLevelService");
		memberLevelService.addPointToLevelTotalPoint(record.getMemberId(), record.getModuleType(),  record.getPoint(), record.getStoreId());
		
		this.upgrade(record.getMemberId(), record.getModuleType(), record.getStoreId());
	}
	
	@CacheEvict(value= "memberLevel", key = "'MemberLevelService_list_' +#memberId")
	@Override
	public void addPointToLevelTotalPoint(int memberId, int moduleType, int point, int storeId){
	    this.memberLevelMapper.addPoint(memberId, moduleType,  point, storeId);
	}
	
	/**
	 * 检查是否有会员级别的资料，如果没有，则生成一个默认的级别
	 * @param memberId
	 * @param moduleType
	 */
	private void checkAndInitMemberLevel(int memberId, int moduleType, int storeId){
		 //1、查看会员，是否有会员级别的记录
		Map<String, MemberLeveLRule> memberLevelRuleMap = SpringContextHolder.getBean("memberLeveLRuleMap");
		MemberLeveLRule memberLeveLRule  = memberLevelRuleMap.get(String.valueOf(moduleType));
		
		MemberLevelService memberLevelService = SpringContextHolder.getBean("memberLevelService");
		if(!this.isMemberLevelExist(memberId, moduleType, storeId)){
			MemberLevel level = memberLeveLRule.createDefaultLevel(memberId, moduleType, storeId);
			memberLevelService.addObj(level);
		}
	}
	
	private void upgrade(int memberId, int moduleType, int storeId){
		Map<String, MemberLeveLRule> memberLevelRuleMap = SpringContextHolder.getBean("memberLeveLRuleMap");
		MemberLeveLRule memberLeveLRule  = memberLevelRuleMap.get(String.valueOf(moduleType));
		
		memberLeveLRule.upgrade(memberId, moduleType, storeId);
	}

	@Override
	public boolean isMemberLevelExist(int memberId, int moduleType, int storeId) {
		MemberLevelExample example = new MemberLevelExample();
		example.createCriteria()
		       .andMemberIdEqualTo(memberId)
		       .andModuleTypeEqualTo(moduleType)
		       .andStoreIdEqualTo(storeId);
		
		return this.memberLevelMapper.countByExample(example) > 0;
	}
	
}