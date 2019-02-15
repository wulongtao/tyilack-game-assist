package com.tyilack.assist.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wulongtao
 */
@Data
public class GameVO {
    @NotNull
    private Integer gameId;

}
