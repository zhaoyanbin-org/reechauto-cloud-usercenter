package com.reechauto.cloud.provider.mapper;

import com.reechauto.cloud.provider.model.MailTemplate;
import com.reechauto.cloud.provider.model.MailTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: mail_template
* @author zhaoyb
*/
@Repository
public interface MailTemplateMapper {
    long countByExample(MailTemplateExample example);

    int deleteByExample(MailTemplateExample example);

    int deleteByPrimaryKey(String templateId);

    int insert(MailTemplate record);

    int insertSelective(MailTemplate record);

    List<MailTemplate> selectByExampleWithBLOBs(MailTemplateExample example);

    List<MailTemplate> selectByExample(MailTemplateExample example);

    MailTemplate selectByPrimaryKey(String templateId);

    int updateByExampleSelective(@Param("record") MailTemplate record, @Param("example") MailTemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") MailTemplate record, @Param("example") MailTemplateExample example);

    int updateByExample(@Param("record") MailTemplate record, @Param("example") MailTemplateExample example);

    int updateByPrimaryKeySelective(MailTemplate record);

    int updateByPrimaryKeyWithBLOBs(MailTemplate record);

    int updateByPrimaryKey(MailTemplate record);
}