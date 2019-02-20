package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class TaskCommandGroupDO {
    private Integer id;
    private Integer taskId;
    private Integer groupId;
    private Integer order;
    private Date gmtCreate;
    private Date gmtModified;
}
