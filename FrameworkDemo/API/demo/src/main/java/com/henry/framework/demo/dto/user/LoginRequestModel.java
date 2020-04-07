package com.henry.framework.demo.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequestModel {

    @NotBlank(message = "手机不能为空")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String vcode;

}