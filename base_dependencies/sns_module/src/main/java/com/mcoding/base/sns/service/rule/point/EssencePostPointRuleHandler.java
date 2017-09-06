package com.mcoding.base.sns.service.rule.point;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mcoding.base.point.bean.MemberPointData;
import com.mcoding.base.sns.bean.post.SnsPost;
import com.mcoding.base.sns.service.post.SnsPostService;
import com.mcoding.base.sns.utils.SnsPointType;
import com.mcoding.base.ui.utils.common.Constant;

/**
 * 加精积分处理
 * @author hzy
 *
 */
@Component
public class EssencePostPointRuleHandler extends BasePointHandler<Integer, Integer, Object>{

	@Resource
	protected SnsPostService snsPostService;
	
	@Override
	public List<MemberPointData> addPoint(Integer postId, Integer isCheck, Object methodResult) {
		if (!Constant.YES_INT.equals(isCheck)) {
			return null;
		}
		
		SnsPost snsPost = snsPostService.queryObjById(postId);
		
		MemberPointData pointData = new MemberPointData();
		pointData.setPoint(SnsPointType.ESSENCE.getPoint());
		pointData.setPointType(SnsPointType.ESSENCE);
		pointData.setSourceId(postId);
		pointData.setStoreId(snsPost.getStoreId());
		pointData.setMember(snsPost.getMember());
		
		List<MemberPointData> list = new ArrayList<>();
		list.add(pointData);
		return list;
	}

}
