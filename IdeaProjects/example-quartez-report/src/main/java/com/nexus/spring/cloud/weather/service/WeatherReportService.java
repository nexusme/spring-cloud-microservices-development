package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.Weather;

public interface WeatherReportService {
    //根据城市id查询天气信息
    Weather getDataByCityId(String cityid);
}
