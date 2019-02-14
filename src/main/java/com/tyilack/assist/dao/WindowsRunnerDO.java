package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

@Data
public class WindowsRunnerDO {
    private Integer id;
    private String name;
    private String programSource;
    private String location;
    private String operation;
    private Integer duration;
    private Integer repeat;
    private Date gmtCreate;
    private Date gmtModified;
}
