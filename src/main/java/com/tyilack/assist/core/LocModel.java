package com.tyilack.assist.core;

import lombok.Data;

/**
 * 位置模型
 * 1 ： gallery_item_id为1的图片，多个图片默认找第一张图片
 * 1,-1 ： gallery_item_id为1的图片，多个图片默认全部找
 * 1,0  ： gallery_item_id为1的图片，多个图片默认找第一张图片
 * @author wulongtao
 */
@Data
public class LocModel {
    private Integer id;
    private Integer loc;

    public LocModel(String location) {
        String[] arr = location.trim().split(",");
        this.id = Integer.parseInt(arr[0]);
        this.loc = arr.length == 1 ? 0 : Integer.parseInt(arr[1]);
    }

}
