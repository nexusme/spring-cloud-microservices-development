package com.nexus.spring.cloud.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
//implement class
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {

        String uri;
        uri = WEATHER_URL+"citykey="+cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {

        String uri =WEATHER_URL+"city="+cityName;
        return this.doGetWeather(uri);
    }


    private WeatherResponse doGetWeather(String uri){//方法重构

        ResponseEntity<String> respString = restTemplate.getForEntity(uri,String.class);

        ObjectMapper mapper  = new ObjectMapper();
        WeatherResponse resp= null;
        String strBody = null;

        if(respString.getStatusCodeValue()==200){
            strBody = respString.getBody();
        }
        try{
            resp = mapper.readValue(strBody,WeatherResponse.class);
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        return resp;

    }
}
