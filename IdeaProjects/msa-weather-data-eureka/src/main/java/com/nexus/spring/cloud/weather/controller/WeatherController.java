package com.nexus.spring.cloud.weather.controller;

import com.nexus.spring.cloud.weather.service.WeatherDataService;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//weather data controller
@RestController
@RequestMapping("/weather")

public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping ("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId){
        System.out.println("cityId:"+cityId);
        return weatherDataService.getDataByCityId(cityId);
    }

    @GetMapping ("/cityName/{cityName}")
    public WeatherResponse getWeatherByCityName(@PathVariable("cityName") String cityName){
        System.out.println("cityName:"+cityName);
        return weatherDataService.getDataByCityName(cityName);
    }
}
