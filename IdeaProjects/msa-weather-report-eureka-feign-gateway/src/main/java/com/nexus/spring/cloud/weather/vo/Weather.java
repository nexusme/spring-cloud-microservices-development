package com.nexus.spring.cloud.weather.vo;

import com.netflix.discovery.provider.Serializer;

import java.io.Serializable;
import java.util.List;

//weather info
public class Weather implements Serializable {
    private static final long serialVersionUID = 5334749781726488511L;
    private Yesterday yesterday;

    private String city;

    private String aqi;

    private List<Forecast> forecast;

    private String ganmao;

    private String wendu;

    public Yesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }



}
