package com.tyilack.assist.util;

/**
 * 游戏状态，0->未启动，1->配置完成可启动任务，2->正在执行任务
 * @author wulongtao
 */
public interface GameStatus {
    /**
     * 0->未启动
     */
    int UNBOOT = 0;
    /**
     * 1->配置完成可启动任务
     */
    int READY = 1;
    /**
     * 2->正在执行任务
     */
    int EXECUTING = 2;

}
