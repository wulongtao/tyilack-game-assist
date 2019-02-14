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
    @Autowired
    private RetResponse retResponse;
    @Autowired
    private ScreenService screenService;

    @GetMapping("/print")
    public RetResult<String> print() {

        screenService.printScreen();

        return retResponse.makeOKRsp();
    }

}
