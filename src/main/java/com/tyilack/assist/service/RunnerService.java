package com.tyilack.assist.service;

import java.util.List;

/**
 * @author wulongtao
 */
public interface RunnerService {

    /**
     * 执行程序
     * @param gameId 游戏ID
     * @return
     */
    List<String> execProgram(Integer gameId);

}
