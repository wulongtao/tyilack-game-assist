package com.tyilack.assist.core;

import com.tyilack.assist.service.GalleryService;
import com.tyilack.assist.util.BaseException;
import com.tyilack.assist.util.NumberUtil;
import com.xnx3.robot.support.CoordBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 命令执行器
 * @author wulongtao
 */
@Slf4j
@Component("executor")
public class CommandExecutor implements Executor {
    private final Robot robot;
    private final GalleryService galleryService;

    @Autowired
    public CommandExecutor(Robot robot, GalleryService galleryService) {
        this.robot = robot;
        this.galleryService = galleryService;
    }


    @Override
    public void execute(String condition, String location, Integer locationClick, Integer offsetX, Integer offsetY, String operation, Integer duration) {
        boolean canExecute = false;
        log.info("读取条件： {}", condition);

        if (Objects.nonNull(condition) && NumberUtil.isNumeric(condition)) {
            String condUrl = galleryService.getImageUrl(Integer.parseInt(condition));
            if (Objects.nonNull(robot.singleImageSearch(condUrl, Robot.SIM_BLUR))) {
                canExecute = true;
            } else {
                log.info("找不到条件： {}", condUrl);
            }
        } else {
            canExecute = true;
        }

        if (!canExecute) {
            return;
        }

        /*
        location字段说明
        1 ： gallery_item_id为1的图片，多个图片默认全部找
        1,-1 ： gallery_item_id为1的图片，多个图片默认全部找
        1,0  ： gallery_item_id为1的图片，多个图片默认找第一张图片
         */
        log.info("读取定位信息： {}", location);
        if (Objects.nonNull(location)) {
            LocModel locModel = new LocModel(location);
            String imageUrl = galleryService.getImageUrl(locModel.getId());
            log.info("图片文件地址：{}", imageUrl);
            List<CoordBean> imageCoordList = robot.imageSearch(imageUrl, Robot.SIM_BLUR);
            log.info("区域找图数量：{}", imageCoordList.size());
            int index = 0;
            for (CoordBean imageCoord : imageCoordList) {
                boolean isHit = Objects.nonNull(imageCoord) && (locModel.getLoc()==-1||locModel.getLoc()==index);
                if (isHit) {
                    log.info("定位操作，location : {},x : {},y : {}, duration: {}", location, imageCoord.getX(), imageCoord.getY(), duration);
                    robot.delay(duration);
                    if (locationClick == 0) {
                        robot.mouseMove(imageCoord.getX()+offsetX, imageCoord.getY()+offsetY);
                    } else {
                        robot.mouseLeftClick(imageCoord.getX()+offsetX, imageCoord.getY()+offsetY);
                    }

                }
                index++;
            }
        }

        log.info("读取按键操作： {}", operation);
        try {
            if (Objects.nonNull(operation)) {
                String[] opArray = operation.split(",");
                for (String opItem : opArray) {
                    String[] actionArr = opItem.split(" ");
                    int count = Integer.parseInt(actionArr[2]);
                    for (int i = 0; i < count; i++) {
                        log.info("按键：{}，延时：{}，第{}次", actionArr[0], actionArr[1], (i+1));
                        robot.delay(Integer.parseInt(actionArr[1])*1000);
                        robot.press(actionArr[0]);
                    }

                }
            }
        } catch (Exception e) {
            log.error("执行指令错误", e);
            throw new BaseException("执行指令错误，operation : "+operation);
        }


    }
}
