package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class ParamDO {
    private Integer id;
    private String name;
    private String desc;
    private String value;
    private Date gmtCreate;
    private Date gmtModified;
}
