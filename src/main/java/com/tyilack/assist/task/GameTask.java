package com.tyilack.assist.task;

import com.tyilack.assist.data.GameTaskCacheModel;
import com.tyilack.assist.service.CommandService;
import com.tyilack.assist.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final DataService dataService;
    private final CommandService commandService;

    @Autowired
    public GameTask(DataService dataService, CommandService commandService) {
        this.dataService = dataService;
        this.commandService = commandService;
    }

    @Scheduled(initialDelay=5000, fixedRate=300000)
    public void run() {
        GameTaskCacheModel taskModel = dataService.getTask();
        if (Objects.isNull(taskModel)) {
            return;
        }
        Integer gameId = taskModel.getGameId();
        Integer groupId = taskModel.getGroupId();
        Integer taskId = taskModel.getTaskId();
        //1、执行任务自带指令集（一般是点击下面Tab做程序的切换）
        commandService.execCommandByGroupId(groupId);

        //2、执行前置指令库的指令
        commandService.execCommandByGameId(gameId);

        //3、依次执行任务里面所有指令集
        commandService.execCommandByTaskId(gameId, taskId);
    }

}
