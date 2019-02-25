package com.tyilack.assist.service.impl;

import com.tyilack.assist.core.Executor;
import com.tyilack.assist.core.Robot;
import com.tyilack.assist.dao.CommandGroupItemDO;
import com.tyilack.assist.dao.WindowsRunnerDO;
import com.tyilack.assist.mapper.CommandMapper;
import com.tyilack.assist.mapper.WindowsRunnerMapper;
import com.tyilack.assist.service.ParamService;
import com.tyilack.assist.service.RunnerService;
import com.tyilack.assist.service.ScreenService;
import com.xnx3.robot.support.CoordBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wulongtao
 */
@Slf4j
@Service
public class RunnerServiceImpl implements RunnerService {
    private final WindowsRunnerMapper windowsRunnerMapper;
    private final ParamService paramService;
    private final ScreenService screenService;
    private final CommandMapper commandMapper;
    private final Robot robot;
    private final Executor executor;

    @Autowired
    public RunnerServiceImpl(WindowsRunnerMapper windowsRunnerMapper, ParamService paramService, ScreenService screenService, CommandMapper commandMapper, Robot robot, Executor executor) {
        this.windowsRunnerMapper = windowsRunnerMapper;
        this.paramService = paramService;
        this.screenService = screenService;
        this.commandMapper = commandMapper;
        this.robot = robot;
        this.executor = executor;
    }

    @Override
    public List<String> execProgram(Integer gameId) {
        List<WindowsRunnerDO> runnerList = windowsRunnerMapper.listWindowsRunnerByGame(gameId);
        if (Objects.isNull(runnerList) || runnerList.size() == 0) {
            return new ArrayList<>();
        }
        Runtime runtime = Runtime.getRuntime();
        String programSource="";

        List<String> dataList = new ArrayList<>();
        try {
            //需要打开的windows程序
            for (WindowsRunnerDO item : runnerList) {
                int repeat = item.getRepeat();

                //程序重复运行的次数
                for (int i = 0; i < repeat; i++) {
                    programSource = item.getProgramSource();
                    log.info("延时 {} ,执行程序 {} ， 次数 {}", item.getDuration(), programSource, (i+1));
                    robot.delay(item.getDuration());
                    runtime.exec(programSource);

                    /**
                     * 开始运行指令集
                     */
                    List<CommandGroupItemDO> commandList = commandMapper.listCommandByGroupId(item.getGroupId());
                    for (CommandGroupItemDO commandItem : commandList) {
                        log.info("延时 {}ms 执行指令 {} - {}", commandItem.getDuration(), commandItem.getLocation(), commandItem.getOperation());
                        executor.execute(commandItem.getCondition(), commandItem.getLocation(), commandItem.getOperation(), commandItem.getDuration());
                    }

                    String screenImage = screenService.printScreen();
                    dataList.add(screenImage);


                }
            }
        } catch (Exception e) {
            log.error("执行"+programSource+"命令出错," + e.getMessage(), e);
            return null;
        }


        return dataList;
    }
}
