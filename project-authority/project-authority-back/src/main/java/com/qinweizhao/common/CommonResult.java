package com.qinweizhao.common;


/**
 * @author qinweizhao
 * @since 2021/9/25
 */
public class CommonResult {

    /**
     * 成功状态码
     */
    private static final int SUCCESS_CODE = 200;

    /**
     * 失败状态码
     */
    private static final int FAILURE_CODE = 400;

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public CommonResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success(Object data) {
        return success(SUCCESS_CODE, "操作成功", data);
    }

    public static CommonResult success(String message, Object data) {
        return success(SUCCESS_CODE, message, data);
    }

    public static CommonResult success(int code, String message, Object data) {
        return new CommonResult(code, message, data);
    }

    public static CommonResult failure(String message) {
        return success(FAILURE_CODE, message, null);
    }

    public static CommonResult failure(String message, Object data) {
        return success(FAILURE_CODE, message, data);
    }

    public static CommonResult failure(int code, String message, Object data) {
        return new CommonResult(code, message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
