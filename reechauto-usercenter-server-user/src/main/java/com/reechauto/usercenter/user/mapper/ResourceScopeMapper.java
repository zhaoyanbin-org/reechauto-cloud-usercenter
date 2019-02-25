package com.reechauto.usercenter.user.mapper;

import com.reechauto.usercenter.user.entity.ResourceScope;
import com.reechauto.usercenter.user.entity.ResourceScopeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: resource_scope
* @author zhaoyb
*/
@Repository
public interface ResourceScopeMapper {
    long countByExample(ResourceScopeExample example);

    int deleteByExample(ResourceScopeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceScope record);

    int insertSelective(ResourceScope record);

    List<ResourceScope> selectByExample(ResourceScopeExample example);

    ResourceScope selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourceScope record, @Param("example") ResourceScopeExample example);

    int updateByExample(@Param("record") ResourceScope record, @Param("example") ResourceScopeExample example);

    int updateByPrimaryKeySelective(ResourceScope record);

    int updateByPrimaryKey(ResourceScope record);
}