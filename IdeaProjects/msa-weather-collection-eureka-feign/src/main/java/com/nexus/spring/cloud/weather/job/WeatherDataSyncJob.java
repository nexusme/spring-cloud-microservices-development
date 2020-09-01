package com.nexus.spring.cloud.weather.job;

import com.nexus.spring.cloud.weather.service.CityClient;
import com.nexus.spring.cloud.weather.service.WeatherDataCollectionService;
import com.nexus.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);



    @Autowired
    private WeatherDataCollectionService weatherDataCollectionService;


    @Autowired
    private CityClient cityClient;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    logger.info("Weather sync is on progress");
    //改为由城市数据API微服务来提供数据
    List<City> cityList = null;
    //System.out.println("Checkpoint before try");
        try {
            logger.info("Try sync.");
            //TODO由城市数据API微服务
            cityList=cityClient.listCity();

           // logger.info("citylist:{}",cityList.size());

        } catch (Exception e) {
         logger.error("Wrong sync.",e);
        }

        //system.out.println("Checkpoint: before for");
        for (City city :cityList){
            System.out.println("Checkpoint: inside for");
            String cityId=city.getCityId();
            logger.info("weather data sync job, cityId:" + cityId);
            weatherDataCollectionService.syncDataByCityId(city.getCityId());

        }
        logger.info("Sync service ends.");
    }
}
