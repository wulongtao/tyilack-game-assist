package com.tyilack.assist.controller;

import com.tyilack.assist.service.RunnerService;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wulongtao
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/game/runner", consumes="application/json", produces="application/json")
public class RunnerController {
    private final RetResponse retResponse;

    @Autowired
    public RunnerController(RetResponse retResponse) {
        this.retResponse = retResponse;
    }


    @PostMapping("/exec")
    public RetResult<Object> exec() {
        return retResponse.makeOKRsp();
    }

}
