package com.tyilack.assist.util;

/**
 * 项目全局异常处理基类
 * @author tyilack
 */
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException() {
    }

    public BaseException(String message) {
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
