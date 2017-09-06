package com.mcoding.base.sns.service.rule;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.service.member.MemberService;
import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.point.bean.level.MemberLevel;
import com.mcoding.base.point.service.MemberLeveLRule;
import com.mcoding.base.point.service.level.MemberLevelService;
import com.mcoding.base.sns.utils.SnsConstant;
import com.mcoding.base.sns.utils.SnsPointType;

/**
 * 实现微社区的会员级别规则，包括初始化规则，升级规则
 * @author hzy
 *
 */
@Component("snsMemberLevelRule")
public class SnsMemberLevelRuleImpl implements MemberLeveLRule {
	
	private static final Integer BASE_UPGRADE_POINT = 10;

	@Resource
	protected MemberLevelService memberLevelService;

	@Resource
	protected MemberService memberService;
	
	@Resource
	protected SnsPointSynchronizer snsMemberPointHandler;

	@Transactional
	@Override
	public void upgrade(int memberId, int moduleType, int storeId) {

		Map<Integer, MemberLevel> levelMap = this.memberLevelService.queryAllByMemberId(memberId);
		if (MapUtils.isEmpty(levelMap)) {
			this.createDefaultLevel(memberId, moduleType, storeId);
			return;
		}

		MemberLevel sourceLevel = levelMap.get(moduleType);
		MemberLevel targetLevel = this.getUpgradeLevel(sourceLevel);

		if (targetLevel ==null || targetLevel.getGrade() ==null || 
				targetLevel.getGrade() <= sourceLevel.getGrade()) {
			return;
		}
		
		int upgradePoint = BASE_UPGRADE_POINT * targetLevel.getGrade() * targetLevel.getGrade();
		Member member = this.memberService.queryObjById(memberId);
		
		this.memberLevelService.modifyObj(targetLevel);
		
		MemberPointData pointData = new MemberPointData();
		pointData.setPointType(SnsPointType.UPGRADE);
		pointData.setPoint(upgradePoint);
		pointData.setMember(member);
		pointData.setStoreId(storeId);
		
		this.snsMemberPointHandler.addPoint(pointData);
	}

	private MemberLevel getUpgradeLevel(MemberLevel sourceLevel) {
		MemberLevel targetLevel = new MemberLevel();

		int point = sourceLevel.getTotalPoint() == null ? 0 : sourceLevel.getTotalPoint();
		if (point >= 30 && point < 130 && sourceLevel.getGrade() < 1) {
			targetLevel.setGrade(1);
			targetLevel.setGradeName("文弱小生");

		} else if (point >= 130 && point < 340 && sourceLevel.getGrade() < 2) {
			targetLevel.setGrade(2);
			targetLevel.setGradeName("健身菜鸟");

		} else if (point >= 340 && point < 650 && sourceLevel.getGrade() < 3) {
			targetLevel.setGrade(3);
			targetLevel.setGradeName("健身能人");

		} else if (point >= 650 && point < 1100 && sourceLevel.getGrade() < 4) {
			targetLevel.setGrade(4);
			targetLevel.setGradeName("健身力士");
			
		} else if (point >= 1100 && point < 1860 && sourceLevel.getGrade() < 5) {
			targetLevel.setGrade(5);
			targetLevel.setGradeName("健身达人");

		} else if (point >= 1860 && point < 2890 && sourceLevel.getGrade() < 6) {
			targetLevel.setGrade(6);
			targetLevel.setGradeName("健身精英");
			
		} else if (point >= 2890 && point < 4280 && sourceLevel.getGrade() < 7) {
			targetLevel.setGrade(7);
			targetLevel.setGradeName("健身红人");

		} else if (point >= 4280 && point < 5800 && sourceLevel.getGrade() < 8) {
			targetLevel.setGrade(8);
			targetLevel.setGradeName("健身明星");

		} else if (point >= 5800 && point < 7700 && sourceLevel.getGrade() < 9) {
			targetLevel.setGrade(9);
			targetLevel.setGradeName("健身大咖");

		} else if (point >= 7700 && point < 10000 && sourceLevel.getGrade() < 10) {
			targetLevel.setGrade(10);
			targetLevel.setGradeName("健身专家");

		} else if (point >= 10000 && point < 12490 && sourceLevel.getGrade() < 11) {
			targetLevel.setGrade(11);
			targetLevel.setGradeName("健身高手");
			
		} else if (point >= 12490 && point < 15380 && sourceLevel.getGrade() < 12) {
			targetLevel.setGrade(12);
			targetLevel.setGradeName("健身大神");

		} else if (point >= 15380 && point < 18840 && sourceLevel.getGrade() < 13) {
			targetLevel.setGrade(13);
			targetLevel.setGradeName("健身狂人");

		} else if (point >= 18840 && point < 22880 && sourceLevel.getGrade() < 14) {
			targetLevel.setGrade(14);
			targetLevel.setGradeName("健身宗师");

		} else if (point >= 22880 && point < 27700 && sourceLevel.getGrade() < 15) {
			targetLevel.setGrade(15);
			targetLevel.setGradeName("健身尊主");

		} else if (point >= 27700 && point < 32800 && sourceLevel.getGrade() < 16) {
			targetLevel.setGrade(16);
			targetLevel.setGradeName("健身领主");
			
		} else if (point >= 32800 && point < 39000 && sourceLevel.getGrade() < 17) {
			targetLevel.setGrade(16);
			targetLevel.setGradeName("健身王者");

		} else if (point >= 39000 && point < 47180 && sourceLevel.getGrade() < 18) {
			targetLevel.setGrade(17);
			targetLevel.setGradeName("健身天王");

		} else if (point >= 47180 && point < 58680 && sourceLevel.getGrade() < 19) {
			targetLevel.setGrade(17);
			targetLevel.setGradeName("健身君王");

		}else if (point>= 58680 && sourceLevel.getGrade() < 20) {
			targetLevel.setGrade(20);
			targetLevel.setGradeName("健身帝王");
		}
		
		targetLevel.setId(sourceLevel.getId());
		targetLevel.setMemberId(sourceLevel.getMemberId());
		targetLevel.setModuleType(sourceLevel.getModuleType());
		return targetLevel;
	}

	@Override
	public MemberLevel createDefaultLevel(int memberId, Integer moduleType, int storeId) {
		MemberLevel level = new MemberLevel();
		level.setMemberId(memberId);
		level.setGrade(0);
		level.setGradeName("新手");
		level.setTotalPoint(0);
		level.setModuleType(SnsConstant.CODE_MODULE_TYPE_SNS);
		level.setStoreId(storeId);
		return level;
	}

}
