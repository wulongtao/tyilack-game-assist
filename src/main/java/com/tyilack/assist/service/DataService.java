package com.tyilack.assist.service;

import com.tyilack.assist.data.GameTaskCacheModel;

/**
 * @author wulongtao
 */
public interface DataService {

    /**
     * 刷新缓存数据
     */
    void refreshDataCache();

    /**
     * 自动获取任务
     * @return
     */
    GameTaskCacheModel getTask();

}
