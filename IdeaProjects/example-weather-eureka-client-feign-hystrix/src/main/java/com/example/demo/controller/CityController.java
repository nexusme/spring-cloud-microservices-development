package com.example.demo.controller;

import com.example.demo.service.CityClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    @Autowired
    private CityClient cityClient;
    @GetMapping("/cities")
    @HystrixCommand(fallbackMethod = "defaultCities")
    public String listCity(){
        //通过feign客户端查找
        String body = cityClient.ListCity();

        return body;
    }

    public String defaultCities(){
        return "City data server is crashed.";
    }
}
