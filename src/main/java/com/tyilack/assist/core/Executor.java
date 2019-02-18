package com.tyilack.assist.core;

/**
 * 执行器接口
 * @author wulongtao
 */
public interface Executor {

    boolean execute(String condition, String location, String operation, Integer duration);

}
