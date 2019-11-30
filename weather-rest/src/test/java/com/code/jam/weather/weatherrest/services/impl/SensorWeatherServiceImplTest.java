package com.code.jam.weather.weatherrest.services.impl;

import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;
import com.code.jam.weather.weatherrest.entities.City;
import com.code.jam.weather.weatherrest.repositories.CityRepository;
import com.code.jam.weather.weatherrest.repositories.SensorRepository;
import com.code.jam.weather.weatherrest.repositories.WeatherRecordRepository;
import com.code.jam.weather.weatherrest.services.SensorWeatherService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class SensorWeatherServiceImplTest {

    @Autowired
    private SensorWeatherService service;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private WeatherRecordRepository weatherRecordRepository;

    @Test
    void shouldSaveNewCityNewSensorData() {
        final SensorWeatherRecord record = new SensorWeatherRecord();
        record.setCityName("Pune");
        record.setSensorName("Pune_01");
        record.setTimestamp(new Date());
        record.setTemperature(13.45F);
        record.setRainFall(1200L);

        service.save(record);

        final City city = cityRepository.findByName(record.getCityName());
        assertNotNull(city);
    }
}
