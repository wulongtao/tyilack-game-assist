package com.tyilack.assist.controller;

import com.tyilack.assist.util.RetResponse;
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
@RequestMapping(value = "/game", consumes="application/json", produces="application/json")
public class GalleryController {
    private final RetResponse retResponse;

    @Autowired
    public GalleryController(RetResponse retResponse) {
        this.retResponse = retResponse;
    }

}
