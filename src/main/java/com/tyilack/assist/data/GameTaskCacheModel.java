package com.tyilack.assist.data;

import lombok.Data;

/**
 * @author wulongtao
 */
@Data
public class GameTaskCacheModel {
    private Integer gameId;
    private Integer taskId;
    private Integer groupId;
    private Long triggerTime;
}
