package com.tyilack.assist.controller;

import com.tyilack.assist.service.CommandService;
import com.tyilack.assist.util.RetResponse;
import com.tyilack.assist.util.RetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wulongtao
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/game/test")
public class TestController {
    private final RetResponse retResponse;
    private final CommandService commandService;

    @Autowired
    public TestController(RetResponse retResponse, CommandService commandService) {
        this.retResponse = retResponse;
        this.commandService = commandService;
    }

    @GetMapping("/group")
    public RetResult<Object> group(Integer gameId, Integer groupId) {
        commandService.execCommandByGroupId(gameId, groupId);
        return retResponse.makeOKRsp();
    }


}
