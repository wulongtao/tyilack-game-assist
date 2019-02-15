package com.tyilack.assist.controller;

import com.tyilack.assist.service.RunnerService;
import com.tyilack.assist.service.ScreenService;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import com.tyilack.assist.vo.GameVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private RetResponse retResponse;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private RunnerService runnerService;

    @PostMapping("/start")
    public RetResult<List<String>> start(@Valid @RequestBody GameVO gameVO) {
        List<String> dataList = runnerService.execProgram(gameVO.getGameId());
        if (Objects.isNull(dataList)) {
            return retResponse.makeErrRsp(screenService.printScreen());
        }

        return retResponse.makeOKRsp(dataList);
    }


}
