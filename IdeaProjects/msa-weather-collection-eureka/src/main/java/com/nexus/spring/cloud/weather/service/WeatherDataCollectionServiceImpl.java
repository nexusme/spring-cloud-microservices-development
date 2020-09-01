package com.nexus.spring.cloud.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService {
    private final static Logger logger = LoggerFactory.getLogger(WeatherDataCollectionServiceImpl.class);

    private static String WEATHER_URI="http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIME_OUT = 1000L;//正常情况下是半个小时


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @Override
        public void syncDataByCityId(String cityId) {
            String uri=WEATHER_URI + "citykey=" + cityId;
            this.saveWeatherData(uri);

        }

        /**
         * 把天气数据放到redis缓存中
         * @param uri
         */
        private void saveWeatherData(String uri){
            String key=uri;
            String strBody= null;
            ValueOperations<String, String> ops =  stringRedisTemplate.opsForValue();
            //缓存没有，再去调用第三方的服务接口来获取
            ResponseEntity<String> restString = restTemplate.getForEntity(uri, String.class);
            if(restString.getStatusCodeValue() == 200){
                strBody= restString.getBody();
            }
            //将数据写入缓存
            ops.set(key,strBody, TIME_OUT, TimeUnit.SECONDS);
        }


}
