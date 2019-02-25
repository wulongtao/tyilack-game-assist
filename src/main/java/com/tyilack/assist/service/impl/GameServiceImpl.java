package com.tyilack.assist.service.impl;

import com.tyilack.assist.mapper.GameMapper;
import com.tyilack.assist.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wulongtao
 */
@Service
public class GameServiceImpl implements GameService {
    private final GameMapper gameMapper;

    @Autowired
    public GameServiceImpl(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    @Override
    public boolean updateGameStatus(Integer gameId, Integer status) {
        int result = gameMapper.updateGameStatusById(gameId, status);
        return result > 0;
    }
}
