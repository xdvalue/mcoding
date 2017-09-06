package com.mcoding.comp.wechat.msg.persistence;

import com.mcoding.comp.wechat.msg.bean.WxMsgRule;
import com.mcoding.comp.wechat.msg.bean.WxMsgRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMsgRuleMapper {
    int countByExample(WxMsgRuleExample example);

    int deleteByExample(WxMsgRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxMsgRule record);

    int insertSelective(WxMsgRule record);

    List<WxMsgRule> selectByExampleWithBLOBs(WxMsgRuleExample example);

    List<WxMsgRule> selectByExample(WxMsgRuleExample example);

    WxMsgRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxMsgRule record, @Param("example") WxMsgRuleExample example);

    int updateByExampleWithBLOBs(@Param("record") WxMsgRule record, @Param("example") WxMsgRuleExample example);

    int updateByExample(@Param("record") WxMsgRule record, @Param("example") WxMsgRuleExample example);

    int updateByPrimaryKeySelective(WxMsgRule record);

    int updateByPrimaryKeyWithBLOBs(WxMsgRule record);

    int updateByPrimaryKey(WxMsgRule record);
    
	public List<WxMsgRule> selectByExampleByPage(WxMsgRuleExample example);
}