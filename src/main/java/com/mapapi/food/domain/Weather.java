package com.mapapi.food.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Alias("weather")
@Data
@EqualsAndHashCode(callSuper=false)
public class Weather {

    private String yrmmdd;
    private String hour;
    private String temperature;
    private String precipitationForm;
    private String humidity;
}
