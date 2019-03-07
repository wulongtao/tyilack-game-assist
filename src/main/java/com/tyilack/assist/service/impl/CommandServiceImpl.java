package com.tyilack.assist.service.impl;

import com.tyilack.assist.core.Executor;
import com.tyilack.assist.dao.CommandGroupItemDO;
import com.tyilack.assist.dao.TaskCommandGroupDO;
import com.tyilack.assist.mapper.CommandMapper;
import com.tyilack.assist.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void execCommandByGroupId(Integer groupId) {
        //1、执行任务自带指令集（一般是点击下面Tab做程序的切换）
        List<CommandGroupItemDO> taskOwnCommandGroupList =  commandMapper.listCommandByGroupId(groupId);
        for (CommandGroupItemDO item : taskOwnCommandGroupList) {
            executor.execute(item.getCondition(), item.getLocation(), item.getOperation(), item.getDuration());
        }
    }

    @Override
    public void execCommandByGameId(Integer gameId) {
        //2、执行前置指令库的指令
        List<CommandGroupItemDO> gamePreCommandList = commandMapper.listPreCommandByGameId(gameId);
        for (CommandGroupItemDO item : gamePreCommandList) {
            executor.execute(item.getCondition(), item.getLocation(), item.getOperation(), item.getDuration());
        }
    }

    @Override
    public void execCommandByTaskId(Integer taskId) {
        //3、依次执行任务里面所有指令集
        List<TaskCommandGroupDO> allTaskGroupList = commandMapper.listTaskCommandGroupByTaskId(taskId);
        for (TaskCommandGroupDO commandGroupDO : allTaskGroupList) {
            execCommandByGroupId(commandGroupDO.getGroupId());
        }
    }
}
