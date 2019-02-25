package com.tyilack.assist.service.impl;

import com.tyilack.assist.dao.GameDO;
import com.tyilack.assist.dao.GameTaskDO;
import com.tyilack.assist.data.GameCacheModel;
import com.tyilack.assist.data.GameTaskCacheModel;
import com.tyilack.assist.mapper.GameMapper;
import com.tyilack.assist.service.DataService;
import com.tyilack.assist.util.DateTimeUtil;
import com.tyilack.assist.util.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author wulongtao
 */
@Service
public class DataServiceImpl implements DataService {
    /**
     * 缓存对象
     */
    private static Map<Integer, GameCacheModel> dataCache = new HashMap<>();

    private final GameMapper gameMapper;

    @Autowired
    public DataServiceImpl(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    @PostConstruct
    public void init() {
        refreshDataCache();
    }

    @Override
    public void refreshDataCache() {
        List<GameDO> readyGameList = gameMapper.listGameByStatus(GameStatus.READY);
        if (Objects.isNull(readyGameList)) {
            return;
        }
        for (GameDO listItem : readyGameList) {
            Integer gameId = listItem.getId();
            if (dataCache.containsKey(gameId)) {
                continue;
            }
            List<GameTaskDO> gameTaskList = gameMapper.listGameTaskByGameId(gameId);
            if (Objects.isNull(gameTaskList)) {
                return;
            }
            saveDataCache(gameId, gameTaskList);
        }

    }

    @Override
    public GameTaskCacheModel getTask() {
        GameTaskCacheModel data = null;
        for (Map.Entry<Integer, GameCacheModel> entry : dataCache.entrySet()) {
            GameCacheModel cacheModel = entry.getValue();
            if (cacheModel.getIsExecuting()) {
                continue;
            }
            boolean canExecuteTask = cacheModel.getCacheList().size() > 0 && DateTimeUtil.currentTimeFromMorning(DateTimeUtil.currentTimeMillis()) > DateTimeUtil.currentTimeFromMorning(Objects.requireNonNull(cacheModel.getCacheList().peek()).getTriggerTime());
            if (canExecuteTask) {
                data = cacheModel.getCacheList().pop();
            }
        }
        return data;
    }

    /**
     * 游戏任务信息存入缓存
     * @param gameId
     * @param gameTaskList
     */
    private void saveDataCache(Integer gameId, List<GameTaskDO> gameTaskList) {
        LinkedList<GameTaskCacheModel> gameTaskCacheList = new LinkedList<>();
        for (GameTaskDO gameTaskItem : gameTaskList) {
            GameTaskCacheModel gameTaskCache = new GameTaskCacheModel();
            gameTaskCache.setGameId(gameId);
            gameTaskCache.setTaskId(gameTaskItem.getId());
            gameTaskCache.setGroupId(gameTaskItem.getGroupId());
            gameTaskCache.setTriggerTime(gameTaskItem.getTriggerTime()==null?0L:gameTaskItem.getTriggerTime().getTime());
            gameTaskCacheList.offer(gameTaskCache);
        }
        GameCacheModel gameCacheModel = new GameCacheModel();
        gameCacheModel.setCacheList(gameTaskCacheList);
        gameCacheModel.setIsExecuting(false);
        dataCache.put(gameId, gameCacheModel);
    }




}
