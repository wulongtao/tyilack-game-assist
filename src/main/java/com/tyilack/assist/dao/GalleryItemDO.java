package com.tyilack.assist.dao;

import lombok.Data;

import java.util.Date;

/**
 * @author wulongtao
 */
@Data
public class GalleryItemDO {
    private Integer id;
    private Integer galleryId;
    private String name;
    private String url;
    private Date gmtCreate;
    private Date gmtModified;
}
