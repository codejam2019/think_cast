package com.code.jam.weather.weatherrest.services;

import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;
import com.code.jam.weather.weatherrest.controllers.model.WeatherRecord;

import java.util.Date;
import java.util.List;

public interface SensorWeatherService {
    void save(SensorWeatherRecord record);
    List<WeatherRecord> findForCity(String city, Date from, Date to);
    List<WeatherRecord> findForSensor(String sensor, Date from, Date to);
}
