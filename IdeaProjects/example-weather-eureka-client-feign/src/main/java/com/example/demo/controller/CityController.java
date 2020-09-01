package com.example.demo.controller;

import com.example.demo.service.CityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    private CityClient cityClient;
    @GetMapping("/cities")
    public String listCity(){
        //通过feign客户端查找
        String body = cityClient.ListCity();

        return body;
    }
}
