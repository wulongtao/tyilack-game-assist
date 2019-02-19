package com.tyilack.assist.data;

import lombok.Data;

import java.util.LinkedList;

/**
 * @author wulongtao
 */
@Data
public class GameCacheModel {
    LinkedList<GameTaskCacheModel> cacheList;
    Boolean isExecuting;
}
