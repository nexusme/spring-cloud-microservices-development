package com.nexus.spring.cloud.weather.service;

import com.nexus.spring.cloud.weather.vo.City;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataClientFallback implements DataClient{
    @Override
    public List<City> listCity() throws Exception {
        List<City> cityList = null;
        cityList = new ArrayList<>();

        City city  = new City();
        city.setCityId("101280601");
        city.setCityName("深圳");
        cityList.add(city);

        return cityList;
    }

    @Override
    public WeatherResponse getDataByCityId(String cityId) {

        return null;
    }
}
