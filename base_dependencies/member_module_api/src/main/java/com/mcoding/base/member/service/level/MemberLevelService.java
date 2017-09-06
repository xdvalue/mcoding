package com.mcoding.base.member.service.level;

import java.util.List;

import com.mcoding.base.member.bean.level.MemberLevel;
import com.mcoding.base.member.bean.member.Member;
import com.mcoding.base.member.bean.point.vo.MemberPointData;
import com.mcoding.base.member.utils.point.PointType;
/**
 * 会员级别与积分管理
 * @author hzy
 *
 */
public interface MemberLevelService {
	
	public void addObj(MemberLevel t);

    public void deleteObjById(int id);

    public void modifyObj(MemberLevel t);

    public List<MemberLevel> queryAllByMemberId(int memberId);
    
	/**
	 * 会员积分管理
	 * @param point 积分，正数添加，负数减少，
	 * @param source 来源，1为发帖积分，2为评论积分
	 * @param member 会员消息
	 * @param refId 根据来源，的对应的id
	 */
    public void addPoint(MemberPointData memberPointData);

	/**
	 * 检查该会员，是否有级别的数据
	 * @param moduleType 由于不同模块，具有不同的积分记录，这里传的就是模块类型
	 * @return 存在返回true,不存在返回false
	 */
	public boolean isMemberLevelExist(int memberId, int moduleType, int storeId);

	public void addPointToLevelTotalPoint(int memberId, int moduleType, int point, int storeId);
}