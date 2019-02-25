package com.tyilack.assist.service;

/**
 * @author wulongtao
 */
public interface GameService {

    /**
     * 跟新游戏状态
     * 游戏状态
     * @param gameId 游戏ID
     * @param status 0->未启动，1->配置完成可启动任务，2->正在执行任务
     * @return 更新结果
     */
    boolean updateGameStatus(Integer gameId, Integer status);

}
