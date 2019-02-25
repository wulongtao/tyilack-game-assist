package com.tyilack.assist.service.impl;

import com.tyilack.assist.dao.ParamDO;
import com.tyilack.assist.mapper.ParamMapper;
import com.tyilack.assist.service.ParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小小黑
 */
@Slf4j
@Service
public class ParamServiceImpl implements ParamService {
    private Map<String, String> paramCache;

    private final ParamMapper paramMapper;

    @Autowired
    public ParamServiceImpl(ParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }


    @PostConstruct
    public void initParamCache() {
        log.info("数据库静态出参数保存到内存中");
        List<ParamDO> paramList = paramMapper.listAllParams();
        paramCache = new HashMap<>(paramList.size());
        for (ParamDO item : paramList) {
            paramCache.put(item.getName(), item.getValue());
        }

    }

    @Override
    public String getImageLocalPath(String relativePath) {
        return paramCache.get("local_image_dir") + relativePath;
    }
}
