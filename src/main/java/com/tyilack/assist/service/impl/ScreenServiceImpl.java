package com.tyilack.assist.service.impl;

import com.tyilack.assist.core.Robot;
import com.tyilack.assist.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wulongtao
 */
@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private Robot robot;

    @Override
    public String printScreen() {
        robot.screenCapture("public/a.png");
        return null;
    }
}
