package com.lemon.api.runner.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author nickjiang
 * @since 2019-08-12
 */
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 接口分类编号
     */
    private Integer apiClassificationId;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口请求方法
     */
    private String method;

    /**
     * 接口url地址
     */
    private String url;

    /**
     * 创建用户
     */
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public Api setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getApiClassificationId() {
        return apiClassificationId;
    }

    public Api setApiClassificationId(Integer apiClassificationId) {
        this.apiClassificationId = apiClassificationId;
        return this;
    }
    public String getName() {
        return name;
    }

    public Api setName(String name) {
        this.name = name;
        return this;
    }
    public String getMethod() {
        return method;
    }

    public Api setMethod(String method) {
        this.method = method;
        return this;
    }
    public String getUrl() {
        return url;
    }

    public Api setUrl(String url) {
        this.url = url;
        return this;
    }
    public Integer getCreateUser() {
        return createUser;
    }

    public Api setCreateUser(Integer createUser) {
        this.createUser = createUser;
        return this;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public Api setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "Api{" +
            "id=" + id +
            ", apiClassificationId=" + apiClassificationId +
            ", name=" + name +
            ", method=" + method +
            ", url=" + url +
            ", createUser=" + createUser +
            ", createTime=" + createTime +
        "}";
    }
}
