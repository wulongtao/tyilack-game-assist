package com.tyilack.assist.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author tyilack
 * @date 2018/4/25
 */
@Slf4j
@Component
public class RetResponse {
    private final static String SUCCESS = "success";
    private final static String SUCCESS_MESSAGE = "";

    public <T> RetResult<T> makeOKRsp() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMessage(SUCCESS_MESSAGE);
    }

    public <T> RetResult<T> makeOKRsp(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMessage(SUCCESS_MESSAGE).setData(data);
    }

    public <T> RetResult<T> makeErrRsp(String msg) {
        msg = parseMessage(msg);
        return new RetResult<T>().setCode(RetCode.FAIL).setMessage(msg);
    }

    public <T> RetResult<T> makeRsp(int code, String msg) {
        String i18nMsg = parseMessage(msg);
        return new RetResult<T>().setCode(code).setMessage(i18nMsg);
    }

    public <T> RetResult<T> makeRsp(int code, String msg, T data) {
        msg = parseMessage(msg);
        return new RetResult<T>().setCode(code).setMessage(msg).setData(data);
    }

    private String parseMessage(String message) {
        return message;
    }
}