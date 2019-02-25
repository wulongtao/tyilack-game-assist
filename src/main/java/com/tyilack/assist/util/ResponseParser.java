package com.tyilack.assist.util;

/**
 *
 * @author tyilack
 * @date 2017/5/16
 */
public interface ResponseParser<T> {
    /**
     * 解析http响应消息，可以扩展解析JSON、XML等格式
     * @param resp
     * @param clazz
     * @return
     */
    T parse(String resp, Class<T> clazz);
}
