package com.bannirui.spring.security.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("role_name")
    private String roleName;

    @TableField("role_desc")
    private String roleDesc;

    @TableField("role_code")
    private String roleCode;

    @TableField("sort")
    private Integer sort;
}
