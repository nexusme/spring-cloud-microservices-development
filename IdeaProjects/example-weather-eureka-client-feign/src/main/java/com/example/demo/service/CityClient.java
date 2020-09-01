package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("example-weather-eureka-city")
public interface CityClient {
    @GetMapping("/cities")
    String ListCity();
}
