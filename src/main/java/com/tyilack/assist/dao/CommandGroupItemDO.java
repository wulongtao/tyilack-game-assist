package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * game_command_group_item
 * @author 小小黑
 */
@Data
public class CommandGroupItemDO {
    private Integer id;
    private Integer groupId;
    private String location;
    private String operation;
    private Integer duration;
    private Date gmtCreate;
    private Date gmtModified;
}
