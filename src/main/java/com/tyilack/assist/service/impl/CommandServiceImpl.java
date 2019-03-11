package com.tyilack.assist.service.impl;

import com.tyilack.assist.core.Executor;
import com.tyilack.assist.dao.CommandGroupDO;
import com.tyilack.assist.dao.CommandGroupItemDO;
import com.tyilack.assist.dao.TaskCommandGroupDO;
import com.tyilack.assist.mapper.CommandMapper;
import com.tyilack.assist.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wulongtao
 */
@Service
public class CommandServiceImpl implements CommandService {
    private final Executor executor;
    private final CommandMapper commandMapper;

    @Autowired
    public CommandServiceImpl(Executor executor, CommandMapper commandMapper) {
        this.executor = executor;
        this.commandMapper = commandMapper;
    }


    @Override
    public void execCommandByGroupId(Integer gameId, Integer groupId) {
        CommandGroupDO commandGroupDO = commandMapper.findCommandGroupById(groupId);
        int repeat = Objects.isNull(commandGroupDO.getRepeat()) ? 1 : commandGroupDO.getRepeat();
        //执行指令集中的指令
        for (int i = 0; i < repeat; i++) {
            List<CommandGroupItemDO> taskOwnCommandGroupList =  commandMapper.listCommandByGroupId(groupId);
            for (CommandGroupItemDO item : taskOwnCommandGroupList) {
                execCommandByGameId(gameId);
                executor.execute(item.getCondition(), item.getLocation(), item.getLocationClick(), item.getOffsetX(), item.getOffsetY(), item.getOperation(), item.getDuration());
            }
        }
    }

    @Override
    public void execCommandByGameId(Integer gameId) {
        //2、执行前置指令库的指令
        List<CommandGroupItemDO> gamePreCommandList = commandMapper.listPreCommandByGameId(gameId);
        for (CommandGroupItemDO item : gamePreCommandList) {
            executor.execute(item.getCondition(), item.getLocation(), item.getLocationClick(), item.getOffsetX(), item.getOffsetY(), item.getOperation(), item.getDuration());
        }
    }

    @Override
    public void execCommandByTaskId(Integer gameId, Integer taskId) {
        //3、依次执行任务里面所有指令集
        List<TaskCommandGroupDO> allTaskGroupList = commandMapper.listTaskCommandGroupByTaskId(taskId);
        for (TaskCommandGroupDO commandGroupDO : allTaskGroupList) {
            execCommandByGroupId(gameId, commandGroupDO.getGroupId());
        }
    }
}
