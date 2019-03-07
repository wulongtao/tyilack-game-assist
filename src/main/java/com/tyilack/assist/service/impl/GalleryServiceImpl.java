package com.tyilack.assist.service.impl;

import com.tyilack.assist.dao.GalleryDO;
import com.tyilack.assist.dao.GalleryItemDO;
import com.tyilack.assist.mapper.GalleryMapper;
import com.tyilack.assist.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tyilack.assist.util.Constants.BASE_STATIC_PATH;

/**
 * @author wulongtao
 */
@Service
public class GalleryServiceImpl implements GalleryService {
    private final GalleryMapper galleryMapper;

    @Autowired
    public GalleryServiceImpl(GalleryMapper galleryMapper) {
        this.galleryMapper = galleryMapper;
    }

    @Override
    public String getImageUrl(Integer id) {
        GalleryItemDO galleryItemDO = galleryMapper.findGalleryItemById(id);
        return BASE_STATIC_PATH + "/" + galleryItemDO.getUrl();
    }
}
