package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.City;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.Path;
import java.util.List;

@FeignClient("example-weather-eureka-data")
public interface WeatherClient {
    @GetMapping("/weather/cityId/{@cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);
}

