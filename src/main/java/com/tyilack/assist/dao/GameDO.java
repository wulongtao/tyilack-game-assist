package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class GameDO {
    private Integer id;
    private String name;
    private String logo;
    private Integer status;
    private Integer completeOperation;
    private Date gmtCreate;
    private Date gmtModified;
}
