package com.tyilack.assist.service;

/**
 * @author wulongtao
 */
public interface CommandService {

    /**
     * 执行任务自带指令集（一般是点击下面Tab做程序的切换）
     * @param groupId 指令集ID
     */
    void execCommandByGroupId(Integer groupId);

    /**
     * 执行前置指令库的指令
     * @param gameId 游戏ID
     */
    void execCommandByGameId(Integer gameId);

    /**
     * 依次执行任务里面所有指令集
     * @param taskId 任务ID
     */
    void execCommandByTaskId(Integer taskId);



}
