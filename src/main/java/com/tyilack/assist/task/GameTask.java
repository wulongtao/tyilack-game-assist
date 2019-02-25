package com.tyilack.assist.task;

import com.tyilack.assist.core.Executor;
import com.tyilack.assist.dao.CommandGroupItemDO;
import com.tyilack.assist.dao.TaskCommandGroupDO;
import com.tyilack.assist.data.GameTaskCacheModel;
import com.tyilack.assist.mapper.CommandMapper;
import com.tyilack.assist.service.DataService;
import com.tyilack.assist.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 游戏定时任务
 * @author wulongtao
 */
@Slf4j
@Component
public class GameTask {
    private final DataService dataService;
    private final CommandMapper commandMapper;
    private final Executor executor;

    @Autowired
    public GameTask(DataService dataService, CommandMapper commandMapper, Executor executor) {
        this.dataService = dataService;
        this.commandMapper = commandMapper;
        this.executor = executor;
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
        List<CommandGroupItemDO> taskOwnCommandGroupList =  commandMapper.listCommandByGroupId(groupId);
        for (CommandGroupItemDO item : taskOwnCommandGroupList) {
            executor.execute(item.getCondition(), item.getLocation(), item.getOperation(), item.getDuration());
        }

        //2、执行前置指令库的指令
        List<CommandGroupItemDO> gamePreCommandList = commandMapper.listPreCommandByGameId(gameId);
        for (CommandGroupItemDO item : gamePreCommandList) {
            executor.execute(item.getCondition(), item.getLocation(), item.getOperation(), item.getDuration());
        }

        //3、依次执行任务里面所有指令集
        List<TaskCommandGroupDO> allTaskGroupList = commandMapper.listTaskCommandGroupByTaskId(taskId);
        for (TaskCommandGroupDO commandGroupDO : allTaskGroupList) {
            List<CommandGroupItemDO> taskCommandGroupList = commandMapper.listCommandByGroupId(commandGroupDO.getGroupId());
            for (CommandGroupItemDO item : taskCommandGroupList) {
                executor.execute(item.getCondition(), item.getLocation(), item.getOperation(), item.getDuration());
            }
        }

    }

}
