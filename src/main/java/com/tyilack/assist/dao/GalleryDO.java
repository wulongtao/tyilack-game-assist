package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class GalleryDO {
    private Integer id;
    private Integer name;
    private Date gmtCreate;
    private Date gmtModified;
}
