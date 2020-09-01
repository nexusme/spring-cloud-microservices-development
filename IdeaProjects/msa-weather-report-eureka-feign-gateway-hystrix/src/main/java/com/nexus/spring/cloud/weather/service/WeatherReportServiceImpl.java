package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.Forecast;
import com.nexus.spring.cloud.weather.vo.Weather;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private DataClient dataClient;

    @Override
    public Weather getDataByCityId(String cityId) {
        //TODO由天气数据API微服务提供数据
        WeatherResponse resp = dataClient.getDataByCityId(cityId);
        Weather data = null;
        if(resp!=null){
            data = resp.getData();
        }

        return data;

    }
}
