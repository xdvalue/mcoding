package com.mcoding.base.sns.utils;

import com.mcoding.base.point.bean.PointType;

public enum SnsPointType implements PointType {
	
	/**积分来源--发帖**/
	POST(2, 101, "微社区:发表帖子积分", SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**积分来源--评论**/
	COMMENT(1, 102, "微社区:发表评论积分", SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**每日登录**/
	LOGIN(2, 103, "微社区:登录积分" , SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**首次登录**/
	FIRST_LOGIN(20, 104, "微社区:首次登录积分", SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**积分来源--文章被评论**/
	BE_COMMENTED(1, 105, "微社区:帖子接受评论积分", SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**积分来源--升级获积分**/
	UPGRADE(0, 106, "微社区:会员升级积分", SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**积分来源--评为精华帖**/
	ESSENCE(15, 107, "微社区:认证精华帖积分", SnsConstant.CODE_MODULE_TYPE_SNS),
	
	/**积分来源--手机认证**/
	VERIFY_PHONE(100, 108, "微社区：绑定手机号码积分", SnsConstant.CODE_MODULE_TYPE_SNS);
	
	private Integer point;
	private Integer sourceType;
	private String sourceName;
	private Integer moduleType;
	
	private SnsPointType(Integer point, Integer sourceType, String sourceName, Integer moduleType) {
		this.point = point;
		this.sourceType = sourceType;
		this.sourceName = sourceName;
		this.moduleType = moduleType;
	}
	public Integer getPoint() {
		if (this.point <=0) {
			throw new IllegalArgumentException("积分不能为空");
		}
		return point;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public String getSourceName() {
		return sourceName;
	}
	public Integer getModuleType() {
		return moduleType;
	}
	public void setPoint(int point) {
		if (point <=0) {
			throw new IllegalArgumentException("积分不能为空");
		}
		this.point = point;
	}
	
}
