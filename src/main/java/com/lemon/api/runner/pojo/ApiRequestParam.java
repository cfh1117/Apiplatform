package com.lemon.api.runner.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-19
 */
@Data
public class ApiRequestParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的接口编号
     */
    private Integer apiId;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 字段类型（string、int...）
     */
    private String paramType;

    /**
     * 参数类型（1：query参数；2：body参数；3：header）
     */
    private Integer type;

    /**
     * 参数值示例
     */
    private String exampleData;

    /**
     * 字段描述
     */
    private String description;
    
    private String value;

}
