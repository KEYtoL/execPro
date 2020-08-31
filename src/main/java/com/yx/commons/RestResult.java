package com.yx.commons;
public class RestResult<T>{
    private Integer code;
    private String message;
    private T data;

    //生成构造方法，getter setter 快捷键  alt+insert
    public RestResult() {

    }

    public RestResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RestResult success(){
        return new RestResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage());
    }

    public static <E> RestResult success(E data){
        return new RestResult(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成功响应结果
     * @param message  自定义消息
     * @param data  响应的数据
     * @param <T>  泛型参数
     * @return  结果类型
     */
    public static <T> RestResult success(String message,T data){
        return new RestResult(ResultCode.SUCCESS.getCode(),message,data);
    }
    public static <T> RestResult failed(String message,T data){
        return new RestResult(ResultCode.FAILED.getCode(),message,data);
    }
    public static RestResult failed(){
        return new RestResult(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage());
    }
    public static RestResult failed(String message){
        return new RestResult(ResultCode.FAILED.getCode(),message);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
