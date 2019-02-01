package com.tyilack.assist.controller;

import com.tyilack.assist.core.Robot;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import com.xnx3.robot.support.CoordBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wulongtao
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RetResponse retResponse;

    @GetMapping("/test1")
    public RetResult<Object> test1() {
        Robot robot = new Robot();
        //在当前屏幕上搜索search.png图片，看起是否存在
        List<CoordBean> list1 = robot.imageSearch("D:\\Project\\JavaProject\\tyilack-game-assist\\src\\main\\resources\\b.png", Robot.SIM_BLUR_VERY);
        System.out.println(list1.size()>0? "搜索到了"+list1.size()+"个目标":"没搜索到");
        if(list1.size()>0){
            for (int i = 0; i < list1.size(); i++) {
                CoordBean coord = list1.get(i);
                System.out.println("搜索到的第"+(i+1)+"个坐标：x:"+coord.getX()+",y:"+coord.getY());
                robot.mouseLeftClick(coord.getX(), coord.getY());
            }
        }

        return retResponse.makeOKRsp();
    }

}
