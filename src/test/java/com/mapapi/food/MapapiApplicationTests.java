package com.mapapi.food;

import com.mapapi.food.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MapapiApplicationTests {

    @Autowired
    WeatherService weatherService;

    private static Logger logger = LoggerFactory.getLogger(MapapiApplicationTests.class);

    @Test
    void test1() throws Exception{
       String s = weatherService.restApiGetWeather();

    }


}
