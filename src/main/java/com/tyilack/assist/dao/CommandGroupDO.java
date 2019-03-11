package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class CommandGroupDO {
    private Integer id;
    private String name;
    private Integer repeat;
    private Date gmtCreate;
    private Date gmtModified;
}
