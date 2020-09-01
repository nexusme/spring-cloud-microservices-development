package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.Forecast;
import com.nexus.spring.cloud.weather.vo.Weather;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {
//
//    @Autowired
//    private WeatherDataService weatherDataService;
@Autowired
private WeatherClient weatherClient;
    @Override
    public Weather getDataByCityId(String cityId) {
        //TODO由天气数据API微服务提供数据
        WeatherResponse resp = weatherClient.getDataByCityId(cityId);
        Weather data = resp.getData();
//        data.setAqi("81");
//        data.setCity("Apple town");
//        data.setGanmao("Cancer");
//        data.setWendu("10-20");
//
//        List<Forecast>  forecastsList = new ArrayList<>();
//        Forecast forecast = new Forecast();
//        forecast.setDate("2019.1.1");
//        forecast.setType("Good");
//        forecast.setFengxiang("South");
//        forecast.setFengli("100");
//        forecast.setHigh("20");
//        forecast.setLow("5");
//        forecastsList.add(forecast);
//        data.setForecast(forecastsList);
////        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return data;

    }
}
