package com.tyilack.assist.controller;

import com.tyilack.assist.service.GameService;
import com.tyilack.assist.service.RunnerService;
import com.tyilack.assist.service.ScreenService;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import com.tyilack.assist.vo.GameVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author wulongtao
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/game", consumes="application/json", produces="application/json")
public class GameController {
    private final RetResponse retResponse;
    private final ScreenService screenService;
    private final RunnerService runnerService;
    private final GameService gameService;

    @Autowired
    public GameController(RetResponse retResponse, ScreenService screenService, RunnerService runnerService, GameService gameService) {
        this.retResponse = retResponse;
        this.screenService = screenService;
        this.runnerService = runnerService;
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public RetResult<List<String>> start(@Valid @RequestBody GameVO gameVO) {
        List<String> dataList = runnerService.execProgram(gameVO.getGameId());
        if (Objects.isNull(dataList)) {
            return retResponse.makeErrRsp(screenService.printScreen());
        }

        return retResponse.makeOKRsp(dataList);
    }

    @PostMapping("/updateGameStatus")
    public RetResult<Object> updateGameStatus(@Valid @RequestBody GameVO gameVO) {
        if (!gameService.updateGameStatus(gameVO.getGameId(), gameVO.getStatus())) {
            return retResponse.makeErrRsp("跟新状态失败，" + gameVO.toString());
        }

        return retResponse.makeOKRsp();
    }


}
