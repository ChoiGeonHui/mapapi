package com.mapapi.food.controller;


import com.mapapi.food.service.WeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @ResponseBody
    @RequestMapping("/weather")
    public String restApiGetWeather(){

        JSONObject jsonObject =new JSONObject();
        return "null";
    }





}
