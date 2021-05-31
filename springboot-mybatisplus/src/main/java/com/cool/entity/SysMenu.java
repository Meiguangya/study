package com.cool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统管理-权限资源表 
 * </p>
 *
 * @author water33
 * @since 2021-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 上级资源ID
     */
    private String parentId;

    /**
     * url
     */
    private String url;

    /**
     * 资源编码
     */
    private String resources;

    /**
     * 资源名称
     */
    private String title;

    /**
     * 资源级别
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sortNo;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 类型 menu、button
     */
    private String type;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;

    /**
     * 1-可用  0-被逻辑删除
     */
    @TableLogic
    private Integer status;

    @TableField(exist = false)
    private List<SysMenu> children;

}
