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
//
//@Service
////implement class
//public class WeatherDataServiceImpl implements WeatherDataService {
//
//    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
//
//    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";
//
//
//
//
//
//    @Autowired
//    //先查缓存 有则取数据
//    //缓存无 则调用接口 获取数据
//    private StringRedisTemplate stringRedisTemplate;//对redis进行封装
//    private static final long TIMEOUT = 10L;
//
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Override
//    public WeatherResponse getDataByCityId(String cityId) {
//
//        String uri;
//        uri = WEATHER_URL+"citykey="+cityId;
//        return this.doGetWeather(uri);
//    }
//
//    @Override
//    public WeatherResponse getDataByCityName(String cityName) {
//
//        String uri =WEATHER_URL+"city="+cityName;
//        return this.doGetWeather(uri);
//    }
//
//
//    private WeatherResponse doGetWeather(String uri){//方法重构
//        String url = uri;
//        String strBody = null;
//        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();//返回 通过get获取缓存中的数据
//
//        if(stringRedisTemplate.hasKey(url)){
//            logger.info("Redis has imported data.");
//            strBody= ops.get(url);
//        }else
//            {
//           // System.out.println("logger test");
//            logger.info("Redis has no data.");
//            ResponseEntity<String> respString = restTemplate.getForEntity(uri,String.class);
//            if(respString.getStatusCodeValue()==200)
//            {
//                strBody = respString.getBody();
//            }
//            //调用完服务 把数据写进缓存
//
//            ops.set(strBody,url,TIMEOUT, TimeUnit.SECONDS);
//
//             }
//        ObjectMapper mapper  = new ObjectMapper();
//        WeatherResponse resp= null;
//        try{
//            resp = mapper.readValue(strBody,WeatherResponse.class);
//        }catch(IOException e)
//        {
//            System.out.println("logger2");
//            logger.error("error!",e);
//            //e.printStackTrace();
//        }
//
//        return resp;
//
//    }
//}
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
    private static  final  String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

//    @Autowired
//    private RestTemplate restTemplate;

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
            logger.info("Redis dont have data");
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
