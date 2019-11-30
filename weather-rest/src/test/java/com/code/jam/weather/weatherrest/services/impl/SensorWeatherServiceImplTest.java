package com.code.jam.weather.weatherrest.services.impl;

import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;
import com.code.jam.weather.weatherrest.entities.City;
import com.code.jam.weather.weatherrest.entities.Sensor;
import com.code.jam.weather.weatherrest.entities.WeatherRecord;
import com.code.jam.weather.weatherrest.repositories.CityRepository;
import com.code.jam.weather.weatherrest.repositories.SensorRepository;
import com.code.jam.weather.weatherrest.repositories.WeatherRecordRepository;
import com.code.jam.weather.weatherrest.services.SensorWeatherService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

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
        final String cityName = RandomString.make();
        final SensorWeatherRecord record = new SensorWeatherRecord();
        record.setCityName(cityName);
        record.setSensorName(record.getCityName() + new Random().nextInt());
        record.setTimestamp(new Date());
        record.setTemperature(13.45F);
        record.setRainFall(1200L);

        service.save(record);

        final City city = cityRepository.findByName(record.getCityName());
        assertNotNull(city);
        assertThat(city.getName(), is(record.getCityName()));

        final Sensor sensor = sensorRepository.findByName(record.getSensorName());
        assertNotNull(sensor);
        assertThat(sensor.getName(), is(record.getSensorName()));

        final List<WeatherRecord> records = weatherRecordRepository.findBySensorId(sensor.getId());
        assertThat(records, hasSize(1));
        final WeatherRecord weatherRecord = records.get(0);
        assertThat(weatherRecord.getRainFall(), is(record.getRainFall()));
        assertThat(weatherRecord.getTemperature(), is(record.getTemperature()));
    }
}
