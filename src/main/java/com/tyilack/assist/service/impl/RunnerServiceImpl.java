package com.tyilack.assist.service.impl;

import com.tyilack.assist.core.Robot;
import com.tyilack.assist.dao.WindowsRunnerDO;
import com.tyilack.assist.mapper.WindowsRunnerMapper;
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
    @Autowired
    private WindowsRunnerMapper windowsRunnerMapper;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private Robot robot;

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
            for (WindowsRunnerDO item : runnerList) {
                int repeat = item.getRepeat();
                robot.delay(10000);
                for (int i = 0; i < repeat; i++) {
                    programSource = item.getProgramSource();
                    runtime.exec(programSource);

                    robot.delay(item.getDuration());
                    CoordBean coordBean = robot.singleImageSearch(item.getLocation(), Robot.SIM_BLUR);
                    robot.mouseLeftClick(coordBean.getX(), coordBean.getY());
                    robot.delay(1000);
                    String screenImage = screenService.printScreen();
                    dataList.add(screenImage);
                }
            }
        } catch (Exception e) {
            log.error("执行"+programSource+"命令出错", e);
            return null;
        }


        return dataList;
    }
}
