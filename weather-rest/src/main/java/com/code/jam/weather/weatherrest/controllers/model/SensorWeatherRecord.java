package com.code.jam.weather.weatherrest.controllers.model;

import lombok.Data;

import java.util.Date;

@Data
public class SensorWeatherRecord {

    private String cityName;
    private String sensorName;
    private Date timestamp;
    private Float temperature;
    private Long rainFall;

}
