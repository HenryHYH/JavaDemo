package com.henry.framework.demo.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.validation.Valid;

import com.henry.framework.demo.dto.user.LoginRequestModel;
import com.henry.framework.demo.dto.user.UserModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public UserModel login(@RequestBody @Valid LoginRequestModel model) {
        UserModel result = new UserModel();
        result.setId(UUID.randomUUID().toString());
        result.setMobile(model.getMobile());
        result.setToken(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));

        return result;
    }

}