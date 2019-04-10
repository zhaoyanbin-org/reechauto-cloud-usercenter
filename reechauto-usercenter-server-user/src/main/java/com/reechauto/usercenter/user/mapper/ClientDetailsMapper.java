package com.reechauto.usercenter.user.mapper;

import com.reechauto.usercenter.user.entity.ClientDetails;
import com.reechauto.usercenter.user.entity.ClientDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: client_details
* @author zhaoyb
*/
@Repository
public interface ClientDetailsMapper {
    long countByExample(ClientDetailsExample example);

    int deleteByExample(ClientDetailsExample example);

    int deleteByPrimaryKey(String clientId);

    int insert(ClientDetails record);

    int insertSelective(ClientDetails record);

    List<ClientDetails> selectByExample(ClientDetailsExample example);

    ClientDetails selectByPrimaryKey(String clientId);

    int updateByExampleSelective(@Param("record") ClientDetails record, @Param("example") ClientDetailsExample example);

    int updateByExample(@Param("record") ClientDetails record, @Param("example") ClientDetailsExample example);

    int updateByPrimaryKeySelective(ClientDetails record);

    int updateByPrimaryKey(ClientDetails record);
}