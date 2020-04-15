package com.bannirui.spring.security.vo.response;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 自定义返回异常
 */
public class CustomException extends RuntimeException {

    private int code;  //异常错误编码

    private String message;  //异常信息

    private CustomException() {
    }

    public CustomException(CustomExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
