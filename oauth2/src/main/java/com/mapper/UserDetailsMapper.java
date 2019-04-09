package com.mapper;

import com.model.UserDetails;
import com.model.UserDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: user_details
* @author zhaoyb
*/
@Repository
public interface UserDetailsMapper {
    long countByExample(UserDetailsExample example);

    int deleteByExample(UserDetailsExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(UserDetails record);

    int insertSelective(UserDetails record);

    List<UserDetails> selectByExample(UserDetailsExample example);

    UserDetails selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") UserDetails record, @Param("example") UserDetailsExample example);

    int updateByExample(@Param("record") UserDetails record, @Param("example") UserDetailsExample example);

    int updateByPrimaryKeySelective(UserDetails record);

    int updateByPrimaryKey(UserDetails record);
}