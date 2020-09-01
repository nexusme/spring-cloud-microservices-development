package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.WeatherResponse;

public interface WeatherDataService  {
  //get weather data by city id
    WeatherResponse getDataByCityId(String cityId);

    //get weather data by city name
    WeatherResponse getDataByCityName (String cityName);
}
