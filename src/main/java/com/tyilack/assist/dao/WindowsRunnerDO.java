package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

@Data
public class WindowsRunnerDO {
    private Integer id;
    private Integer gameId;
    private Integer groupId;
    private String name;
    private String programSource;
    private Integer duration;
    private Integer repeat;
    private Date gmtCreate;
    private Date gmtModified;
}
