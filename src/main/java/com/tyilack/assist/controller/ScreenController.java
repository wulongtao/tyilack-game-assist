package com.tyilack.assist.controller;

import com.tyilack.assist.service.ScreenService;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wulongtao
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/game/screen", consumes="application/json", produces="application/json")
public class ScreenController {
    private final RetResponse retResponse;
    private final ScreenService screenService;

    @Autowired
    public ScreenController(RetResponse retResponse, ScreenService screenService) {
        this.retResponse = retResponse;
        this.screenService = screenService;
    }

    @GetMapping("/print")
    public RetResult<String> print() {

        String filePath = screenService.printScreen();

        return retResponse.makeOKRsp(filePath);
    }

}
