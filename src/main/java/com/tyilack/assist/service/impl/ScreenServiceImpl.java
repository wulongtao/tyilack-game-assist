package com.tyilack.assist.service.impl;

import com.tyilack.assist.core.Robot;
import com.tyilack.assist.dao.ParamDO;
import com.tyilack.assist.mapper.ParamMapper;
import com.tyilack.assist.service.ScreenService;
import com.tyilack.assist.util.BaseException;
import com.tyilack.assist.util.Constants;
import com.tyilack.assist.util.DateTimeUtil;
import com.tyilack.assist.util.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

/**
 * @author wulongtao
 */
@Service
public class ScreenServiceImpl implements ScreenService {
    private final Robot robot;
    private final ParamMapper paramMapper;

    @Autowired
    public ScreenServiceImpl(Robot robot, ParamMapper paramMapper) {
        this.robot = robot;
        this.paramMapper = paramMapper;
    }

    @Override
    public String printScreen() {

        ThreadUtil.sleep(3000);
        ParamDO screenCaptureInfo = paramMapper.findParamByName("screen_capture_base_path");
        if (Objects.isNull(screenCaptureInfo)) {
            throw new BaseException("截屏目录未配置");
        }


        String filePath = screenCaptureInfo.getValue() + DateTimeUtil.currentDateStr() + "/" + UUID.randomUUID().toString() + ".png";
        if (!robot.screenCapture(Constants.BASE_STATIC_PATH + filePath)) {
            throw new BaseException("截屏失败");
        }

        ParamDO domainUrlInfo = paramMapper.findParamByName("domain_url");
        if (Objects.isNull(domainUrlInfo)) {
            return null;
        }

        return domainUrlInfo.getValue() + filePath;
    }
}
