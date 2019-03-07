package com.tyilack.assist.mapper;

import com.tyilack.assist.dao.GalleryItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wulongtao
 */
@Mapper
@Repository
public interface GalleryMapper {

    /**
     * 根据ID查找图片
     * @param id id
     * @return 图片对象
     */
    @Select("SELECT `url` FROM `game_gallery_item` WHERE `id`=#{id}")
    GalleryItemDO findGalleryItemById(@Param("id") Integer id);

}
