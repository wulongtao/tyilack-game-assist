package com.tyilack.assist.core;

import com.tyilack.assist.util.BaseException;
import com.xnx3.robot.support.CoordBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 命令执行器
 * @author wulongtao
 */
@Slf4j
@Component("executor")
public class CommandExecutor implements Executor {
    @Autowired
    private Robot robot;


    /**
     * 指令执行，operation字段写法
     * q 3 2,w 20 1 : 三秒钟后按下q键循环两次，然后在20秒后按下w键循环一次
     * @param condition
     * @param location
     * @param operation
     * @param duration
     * @return
     */
    @Override
    public boolean execute(String condition, String location, String operation, Integer duration) {
        boolean canExecute = false;
        if (Objects.nonNull(condition)) {
            if (Objects.nonNull(robot.singleImageSearch(condition, Robot.SIM_BLUR))) {
                canExecute = true;
            }
        } else {
            canExecute = true;
        }

        if (!canExecute) {
            return false;
        }


        if (Objects.nonNull(location)) {
            CoordBean imageCoord = robot.singleImageSearch(location, Robot.SIM_BLUR);
            if (Objects.nonNull(imageCoord)) {
                robot.delay(duration);
                robot.mouseLeftClick(imageCoord.getX(), imageCoord.getY());
            }
        }

        try {
            if (Objects.nonNull(operation)) {
                String[] opArray = operation.split(",");
                for (String opItem : opArray) {
                    String[] actionArr = opItem.split(" ");
                    int count = Integer.parseInt(actionArr[2]);
                    for (int i = 0; i < count; i++) {
                        robot.delay(Integer.parseInt(actionArr[1]));
                        robot.press(actionArr[0]);
                    }

                }
            }
        } catch (Exception e) {
            log.error("执行指令错误", e);
            throw new BaseException("执行指令错误，operation : "+operation);
        }



        return true;
    }
}
