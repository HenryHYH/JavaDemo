package com.henry.framework.demo.rest;

import com.alibaba.nacos.api.config.annotation.NacosValue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean userLocalCache;

    @GetMapping(value = "/get")
    public boolean get() {
        return userLocalCache;
    }

}