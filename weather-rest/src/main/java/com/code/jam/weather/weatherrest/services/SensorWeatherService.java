package com.code.jam.weather.weatherrest.services;

import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;

public interface SensorWeatherService {
    void save(SensorWeatherRecord record);
}
