package com.tyilack.assist.util;

import com.google.gson.Gson;

/**
 *
 * @author giantan
 * @date 2017/5/16
 */
public class JsonResponseParser<T> implements ResponseParser<T> {

    @Override
    public T parse(String resp, Class<T> clazz) {
        try {
            return new Gson().fromJson(resp, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
