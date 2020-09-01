package com.nexus.spring.cloud.weather.controller;

import com.nexus.spring.cloud.weather.service.CityDataService;
import com.nexus.spring.cloud.weather.service.WeatherDataService;
import com.nexus.spring.cloud.weather.service.WeatherReportService;
import com.nexus.spring.cloud.weather.vo.City;
import com.nexus.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//weather data controller
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private WeatherReportService weatherReportService;
    @Autowired
    private CityDataService cityDataService;
    @GetMapping ("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        //System.out.println("cityId:"+cityId);
        model.addAttribute("title","Nexus's Weather Forecast");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList", cityDataService.listCity());
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report","reportModel",model);
    }

}
