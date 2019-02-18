package com.tyilack.assist.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 游戏定时任务
 * @author wulongtao
 */
@Slf4j
@Component
public class GameTask {
    private Integer runningTaskId;
    private boolean isExecuting = false;

    @Scheduled(initialDelay=5000, fixedRate=300000)
    public void run() {
        // 若任务正在执行，那么直接退出
        if (isExecuting) {
            return;
        }

        if (Objects.isNull(runningTaskId)) {

        }




    }

}
