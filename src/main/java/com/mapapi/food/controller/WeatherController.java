package com.mapapi.food.controller;


import com.mapapi.food.domain.Weather;
import com.mapapi.food.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @ResponseBody
    @RequestMapping("/weather")
    @Scheduled(cron = "0 43 * * * MON-FRI") //월 ~ 금, 매 시간마다 43분에 실행한다.
    public String restApiGetWeather() throws Exception {

         String result = weatherService.restApiGetWeather();

        return "result : " + result;
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        return "testSuccess";
    }

//    @ResponseBody
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showWeather(Model model){
        Weather weather = weatherService.selectWeather();
        model.addAttribute("weather", weather);
        return "show";
    }








}
