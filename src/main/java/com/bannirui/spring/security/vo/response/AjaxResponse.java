package com.bannirui.spring.security.vo.response;

import lombok.Data;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 封装返回体
 */
@Data
public class AjaxResponse {

    private boolean isok;  // 请求是否成功
    private int code;  // 请求状态码
    private String message;  // 响应信息
    private Object data;  // 响应数据

    private AjaxResponse() {

    }

    // 请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e) {

        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(false);
        resultBean.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage());  // 用户输入异常
        } else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()) {
            resultBean.setMessage(e.getMessage());  // 系统服务异常
        } else {
            resultBean.setMessage("系统出现未知异常");  // 其他未知异常
        }
        return resultBean;
    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

}
