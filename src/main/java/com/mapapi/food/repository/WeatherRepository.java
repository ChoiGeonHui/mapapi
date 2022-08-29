package com.mapapi.food.repository;

import com.mapapi.food.domain.Weather;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeatherRepository {


    int insertWeather(Weather weather);

    Weather selectWeather(Weather weather);


}
