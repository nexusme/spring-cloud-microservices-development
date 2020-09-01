package com.nexus.spring.cloud.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static  final  String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";



    @Autowired
    private StringRedisTemplate stringRedisTemplate;
   //  private final  static long TIME_OUT = 1800L;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri=WEATHER_URI + "citykey=" + cityId;
        return  doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri=WEATHER_URI + "city=" + cityName;
        return doGetWeather(uri);
    }

//    @Override
//    public void syncDataByCityId(String cityId) {
//        String uri=WEATHER_URI + "citykey=" + cityId;
//        this.saveWeatherData(uri);
//
//    }

    /**
     * 把天气数据放到redis缓存中
     * @param uri
     */
//    private void saveWeatherData(String uri){
//        String key=uri;
//        String strBody= null;
//        ValueOperations<String, String> ops =  stringRedisTemplate.opsForValue();
//        //缓存没有，再去调用第三方的服务接口来获取
//        ResponseEntity<String> restString = restTemplate.getForEntity(uri, String.class);
//        if(restString.getStatusCodeValue() == 200){
//            strBody= restString.getBody();
//        }
//        //将数据写入缓存
//        ops.set(key,strBody, TIME_OUT, TimeUnit.SECONDS);
//    }
    /**
     *  doGetWeather 根据相应得uri来获取天气数据信息
     * @param uri
     * @return
     */
    private WeatherResponse doGetWeather(String uri){

        String key=uri;
        String strBody= null;
        // 先查缓存，缓存如果有数据就先去取缓存中的数据
        ValueOperations<String, String> ops =  stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey(key)){
            logger.info("Redis has data");
            strBody = ops.get(key);
        }else {
            logger.info("Redis don't have data");
            //缓存没有，再去调用第三方的服务接口来获取
            //抛出异常
            throw new RuntimeException("No source to have data.");
//            ResponseEntity<String> restString = restTemplate.getForEntity(uri, String.class);
//            if(restString.getStatusCodeValue() == 200){
//                strBody= restString.getBody();
//            }
//            //将数据写入缓存
//            ops.set(key,strBody, TIME_OUT, TimeUnit.SECONDS);
        }

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        try {
            resp=mapper.readValue(strBody ,WeatherResponse.class);
        }catch (IOException e){
            logger.error("error!",e);
        }
        return resp;
    }
}
