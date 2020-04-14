package com.bannirui.spring.security.config.exception;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 自定义异常状态码
 */
public enum CustomExceptionType {
    USER_INPUT_ERROR(400, "用户输入异常"),
    SYSTEM_ERROR(500, "系统服务异常"),
    OTHER_ERROR(999, "其他未知异常");

    private String typeDesc;  // 异常类型中文描述
    private int code;  // code

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public int getCode() {
        return code;
    }
}
