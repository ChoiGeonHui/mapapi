package com.mapapi.food.service;

import com.mapapi.food.domain.Weather;
import com.mapapi.food.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    private static Logger logger = LoggerFactory.getLogger(WeatherService.class);



    /**
     * 초단기 실황
     * @return
     * @throws Exception
     */
    @Transactional
    public String restApiGetWeather() throws Exception {

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmm");
        String yrmmdd = simpleDateFormat.format(date);
//        String baseTime = "0900";
        String baseTime = simpleDateFormat2.format(date); // 반드시 40 ~ 59분 사이에 실행해야 함

        //외부 api 주소
        String url2 = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        url2 = url2 + "?ServiceKey=kr0T%2Bt9ZFkJ3hE4dpJ%2B8gMma37hVQk0Z48%2FLm8k7dXNavFaoYVtvUoET1j%2FJi9Met0V2cbo6pngk36GFHR9u2Q%3D%3D&pageNo=1&numOfRows=10&dataType=JSON&nx=60&ny=125";
        url2 = url2 + "&base_date=" + yrmmdd;
        url2 = url2 + "&base_time=" + baseTime;

        URL url = new URL(url2);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        String result = sb.toString();

        JSONObject jsonObj_1 = new JSONObject(result);
        JSONObject response = (JSONObject) jsonObj_1.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");

        JSONArray jsonArray = items.getJSONArray("item");

//        String response = jsonObj_1.getString("response");
//
//        // response로 부터 body 찾기
//        JSONObject jsonObj_2 = new JSONObject(response);
//        String body = jsonObj_2.getString("body");
//
//        // body로 부터 items 찾기
//        JSONObject jsonObj_3 = new JSONObject(body);
//        String items = jsonObj_3.getString("items");
//
//        // items로부터 itemlist 를 받기
//        JSONObject jsonObj_4 = new JSONObject(items);

        // 기온, 날씨, 습도 설정
        String temperature = "", precipitationForm = "", humidity = "";


        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject j = jsonArray.getJSONObject(i);

            switch (j.getString("category")) {
                case "PTY":
                    precipitationForm = j.getString("obsrValue");
                case "REH":
                    humidity = j.getString("obsrValue");
                case "T1H":
                    temperature = j.getString("obsrValue");
                    break;
            }
            j = null;
        }

        //날씨 변환
        precipitationForm = precipitationFormChange(precipitationForm);


        Weather weather = new Weather();

        //시간 설정 1100 -> 11:00
        String x = jsonArray.getJSONObject(0).getString("baseTime");
        StringBuilder hour = new StringBuilder(x);
        hour.insert(2,":");

        weather.setYrmmdd(yrmmdd);
        weather.setHour(hour.toString());
        weather.setTemperature(temperature);
        weather.setHumidity(humidity);
        weather.setPrecipitationForm(precipitationForm);

        int chk = insertWeather(weather); // DB insert

        return "success";
    }

    private String precipitationFormChange(String precipitationForm) {

        switch (precipitationForm) {
            case "0":
                return "맑음";
            case "1":
                return "비";
            case "2":
                return "비/눈";
            case "3":
                return "눈";
            case "5":
                return "빗방울";
            case "6":
                return "빗방울눈날림";
            case "7":
                return "눈날림";
        }
        return null;
    }

    @Transactional
    public int insertWeather(Weather weather) {
        return weatherRepository.insertWeather(weather);
    }


    @Transactional
    public Weather selectWeather(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String yrmmdd = simpleDateFormat.format(date);
        Weather weather = new Weather();
        weather.setYrmmdd(yrmmdd);
        weather = weatherRepository.selectWeather(weather);

        return weather;
    }


}
