package com.mcoding.comp.wechat.redpack.persistence;

import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecord;
import com.mcoding.comp.wechat.redpack.bean.WxRedpackSendRecordExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WxRedpackSendRecordMapper {
    int countByExample(WxRedpackSendRecordExample example);

    int deleteByExample(WxRedpackSendRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxRedpackSendRecord record);

    int insertSelective(WxRedpackSendRecord record);

    List<WxRedpackSendRecord> selectByExample(WxRedpackSendRecordExample example);

    WxRedpackSendRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxRedpackSendRecord record, @Param("example") WxRedpackSendRecordExample example);

    int updateByExample(@Param("record") WxRedpackSendRecord record, @Param("example") WxRedpackSendRecordExample example);

    int updateByPrimaryKeySelective(WxRedpackSendRecord record);

    int updateByPrimaryKey(WxRedpackSendRecord record);
    
    List<WxRedpackSendRecord> selectByExampleByPage(WxRedpackSendRecordExample example);
    
    List<Map<String, Integer>> countStatus(WxRedpackSendRecordExample example);
}