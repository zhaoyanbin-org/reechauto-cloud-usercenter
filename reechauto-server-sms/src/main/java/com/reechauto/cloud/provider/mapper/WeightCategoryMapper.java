package com.reechauto.cloud.provider.mapper;

import com.reechauto.cloud.provider.model.WeightCategory;
import com.reechauto.cloud.provider.model.WeightCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* 绿驰汽车
* tableName: weight_category
* @author zhaoyb
*/
@Repository
public interface WeightCategoryMapper {
    long countByExample(WeightCategoryExample example);

    int deleteByExample(WeightCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WeightCategory record);

    int insertSelective(WeightCategory record);

    List<WeightCategory> selectByExample(WeightCategoryExample example);

    WeightCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WeightCategory record, @Param("example") WeightCategoryExample example);

    int updateByExample(@Param("record") WeightCategory record, @Param("example") WeightCategoryExample example);

    int updateByPrimaryKeySelective(WeightCategory record);

    int updateByPrimaryKey(WeightCategory record);
}