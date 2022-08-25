package com.mapapi.food.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("foodShop")
@Data
@EqualsAndHashCode(callSuper=false)
public class FoodShop {

    //식별자
    private long seq;

    //식당 이름
    private String name;

    //위도
    private String latitude;

    //경도
    private String longitude;

    //삭제 여부
    private String delYN;

    //등록자
    private String createdBy;

    //등록 일자
    private Date createdDate;

    //수정자
    private String modeifiedBy;

    //수정일자
    private Date modeifieDate;


}
