package com.nexus.spring.cloud.weather.controller;


import com.nexus.spring.cloud.weather.service.DataClient;
import com.nexus.spring.cloud.weather.service.WeatherReportService;
import com.nexus.spring.cloud.weather.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.Data;
import java.util.List;

//weather data controller
@RestController
@RequestMapping("/report")
public class WeatherReportController {
    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService weatherReportService;

    @Autowired
    private DataClient dataClient;

    @GetMapping ("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        //TODO改为由城市数据API微服务提供数据

        List<City> cityList = null;

        try{
            cityList = dataClient.listCity();

        }catch (Exception e){
            logger.error("Exception",e);
        }

        model.addAttribute("title","Nexus's Weather Forecast");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report","reportModel",model);
    }

}
