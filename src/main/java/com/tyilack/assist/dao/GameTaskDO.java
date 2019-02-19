package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class GameTaskDO {
    private Integer id;
    private Integer gameId;
    private Integer groupId;
    private Date triggerTime;
    private Integer order;
    private Date gmtCreate;
    private Date gmtModified;
}
