package com.nexus.spring.cloud.weather.service;

public interface WeatherDataCollectionService {
    //根据城市id同步天气数据
    void syncDataByCityId(String cityId);
}
