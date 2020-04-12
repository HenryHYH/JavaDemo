package com.henry.framework.demo.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.henry.framework.core.dto.exception.BusinessException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/exception")
    public String businessException() {
        throw new BusinessException(String.format("Hello Exception at %s", getNow()));
    }

    private String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }

}