package com.reechauto.cloud.provider.mapper;

import com.reechauto.cloud.provider.model.MailRecord;
import com.reechauto.cloud.provider.model.MailRecordExample;
import com.reechauto.cloud.provider.model.MailRecordWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: mail_record
* @author zhaoyb
*/
@Repository
public interface MailRecordMapper {
    long countByExample(MailRecordExample example);

    int deleteByExample(MailRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MailRecordWithBLOBs record);

    int insertSelective(MailRecordWithBLOBs record);

    List<MailRecordWithBLOBs> selectByExampleWithBLOBs(MailRecordExample example);

    List<MailRecord> selectByExample(MailRecordExample example);

    MailRecordWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MailRecordWithBLOBs record, @Param("example") MailRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") MailRecordWithBLOBs record, @Param("example") MailRecordExample example);

    int updateByExample(@Param("record") MailRecord record, @Param("example") MailRecordExample example);

    int updateByPrimaryKeySelective(MailRecordWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MailRecordWithBLOBs record);

    int updateByPrimaryKey(MailRecord record);
}