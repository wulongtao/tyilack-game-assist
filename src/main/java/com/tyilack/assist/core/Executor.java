package com.tyilack.assist.core;

/**
 * 执行器接口
 * @author wulongtao
 */
public interface Executor {

    /**
     * 指令执行，operation字段写法
     * q 3 2,w 20 1 : 三秒钟后按下q键循环两次，然后在20秒后按下w键循环一次
     * @param condition 执行条件
     * @param location 位置
     * @param operation 操作
     * @param duration 延时
     */
    void execute(String condition, String location, String operation, Integer duration);

}
