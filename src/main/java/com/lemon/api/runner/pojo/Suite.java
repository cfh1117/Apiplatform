package com.lemon.api.runner.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-28
 */
@Data
public class Suite implements Serializable {

    private static final long serialVersionUID = 1L;
 
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联项目id
     */
    private Integer projectId;

    /**
     * 集合名字
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
