package com.reechauto.usercenter.user.mapper;

import com.reechauto.usercenter.user.entity.ResourceServer;
import com.reechauto.usercenter.user.entity.ResourceServerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: resource_server
* @author zhaoyb
*/
@Repository
public interface ResourceServerMapper {
    long countByExample(ResourceServerExample example);

    int deleteByExample(ResourceServerExample example);

    int deleteByPrimaryKey(String resourceId);

    int insert(ResourceServer record);

    int insertSelective(ResourceServer record);

    List<ResourceServer> selectByExample(ResourceServerExample example);

    ResourceServer selectByPrimaryKey(String resourceId);

    int updateByExampleSelective(@Param("record") ResourceServer record, @Param("example") ResourceServerExample example);

    int updateByExample(@Param("record") ResourceServer record, @Param("example") ResourceServerExample example);

    int updateByPrimaryKeySelective(ResourceServer record);

    int updateByPrimaryKey(ResourceServer record);
}