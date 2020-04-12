package com.henry.framework.democlient.rest;

import com.henry.framework.core.dto.result.ResultData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/echo")
public class EchoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        ResultData<String> echo = restTemplate.exchange("http://Framework/echo/hello/" + name, HttpMethod.GET, null,
                new ParameterizedTypeReference<ResultData<String>>() {
                }).getBody();

        return "Json = " + echo;
    }

}