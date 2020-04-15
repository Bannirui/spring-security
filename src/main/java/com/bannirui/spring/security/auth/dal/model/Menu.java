package com.bannirui.spring.security.auth.dal.model;

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
@TableName("sys_menu")
public class Menu implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("menu_name")
    private String menuName;

    @TableField("url")
    private String url;
}
