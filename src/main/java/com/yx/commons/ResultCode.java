package com.yx.commons;

/**
 * 响应结果枚举类
 * @author LG
 */
public enum ResultCode {
    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败");

    private Integer code;
    private String message;

    ResultCode() {
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
