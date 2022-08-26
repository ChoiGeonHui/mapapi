package com.mapapi.food.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Alias("weather")
@Data
@EqualsAndHashCode(callSuper=false)
public class Weather {

    //년월일
    private String yrmmdd;

    //시간
    private String hour;

    //기온
    private String temperature;

    //강수형태 (날씨)
    private String precipitationForm;

    //습도
    private String humidity;
}
