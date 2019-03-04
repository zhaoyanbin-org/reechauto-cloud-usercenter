package com.reechauto.cloud.provider.model;

import java.io.Serializable;

/**
* 绿驰汽车
* tableName: weight_category
* @author zhaoyb
*/
public class WeightCategory implements Serializable {

    private Long id;
    /**
     * 资源说明符，根据它来指定某一特定的资源
     */
    private String category;
    /**
     * 权重
     */
    private Integer weight;
    /**
     * 对该资源的说明
     */
    private String remark;
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}