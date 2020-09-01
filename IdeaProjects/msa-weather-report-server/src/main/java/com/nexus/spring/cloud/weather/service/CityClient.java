package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("example-weather-eureka-city")
public interface CityClient {
    @GetMapping("/cities")
    List<City> listCity() throws Exception;
}

