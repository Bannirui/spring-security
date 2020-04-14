package com.bannirui.spring.security.dal.model;

import com.bannirui.spring.security.config.auth.MyUserDetails;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Data
@TableName("sys_user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:MM:SS")
    @TableField("create_time")
    private Date createTime;

    // User类转换MyUserDetails
    public MyUserDetails convert2MyUserDetails() {
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUsername(this.username);
        myUserDetails.setPassword(this.password);
        return myUserDetails;
    }
}
