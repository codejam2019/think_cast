package com.code.jam.weather.weatherrest.services.impl;

import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;
import com.code.jam.weather.weatherrest.entities.City;
import com.code.jam.weather.weatherrest.entities.Sensor;
import com.code.jam.weather.weatherrest.entities.WeatherRecord;
import com.code.jam.weather.weatherrest.repositories.CityRepository;
import com.code.jam.weather.weatherrest.repositories.SensorRepository;
import com.code.jam.weather.weatherrest.repositories.WeatherRecordRepository;
import com.code.jam.weather.weatherrest.services.SensorWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SensorWeatherServiceImpl implements SensorWeatherService {

    private final CityRepository cityRepository;
    private final SensorRepository sensorRepository;
    private final WeatherRecordRepository weatherRecordRepository;

    @Autowired
    public SensorWeatherServiceImpl(final CityRepository cityRepository, final SensorRepository sensorRepository,
                                    final WeatherRecordRepository weatherRecordRepository) {
        this.cityRepository = cityRepository;
        this.sensorRepository = sensorRepository;
        this.weatherRecordRepository = weatherRecordRepository;
    }

    @Override
    public void save(final SensorWeatherRecord record) {
        final City city = saveCity(record);
        final Sensor sensor = saveSensor(record, city);
        saveRecord(record, sensor);
    }

    @Override
    public List<com.code.jam.weather.weatherrest.controllers.model.WeatherRecord> findForCity(final String city,
                                      final Date from, Date to) {
        final List<WeatherRecord> records = new ArrayList<>();
        if (from != null) {
            if (to == null) {
                to = new Date();
            }
            records.addAll(weatherRecordRepository.findByCity(city, from, to));
        } else {
            records.addAll(weatherRecordRepository.findByCity(city));
        }
        return records.stream().map(this::adapt).collect(Collectors.toList());
    }

    @Override
    public List<com.code.jam.weather.weatherrest.controllers.model.WeatherRecord> findForSensor(final String sensor,
                                                                                              final Date from, Date to) {
        final List<WeatherRecord> records = new ArrayList<>();
        if (from != null) {
            if (to == null) {
                to = new Date();
            }
            records.addAll(weatherRecordRepository.findBySensor(sensor, from, to));
        } else {
            records.addAll(weatherRecordRepository.findBySensor(sensor));
        }
        return records.stream().map(this::adapt).collect(Collectors.toList());
    }

    private com.code.jam.weather.weatherrest.controllers.model.WeatherRecord adapt(final WeatherRecord r) {
        final com.code.jam.weather.weatherrest.controllers.model.WeatherRecord model = new com.code.jam.weather.weatherrest.controllers.model.WeatherRecord();
        model.setTimestamp(r.getTimestamp());
        model.setTemperature(r.getTemperature());
        model.setRainFall(r.getRainFall());
        return model;
    }

    private void saveRecord(final SensorWeatherRecord record, final Sensor sensor) {
        final WeatherRecord weatherRecord = new WeatherRecord();
        weatherRecord.setSensorId(sensor.getId());
        weatherRecord.setRainFall(record.getRainFall());
        weatherRecord.setTemperature(record.getTemperature());
        weatherRecord.setTimestamp(record.getTimestamp());
        weatherRecordRepository.save(weatherRecord);
    }

    private Sensor saveSensor(final SensorWeatherRecord record, final City city) {
        Sensor sensor = sensorRepository.findByName(record.getSensorName());
        if (Objects.isNull(sensor)) {
            sensor = new Sensor();
            sensor.setName(record.getSensorName());
            sensor.setCityId(city.getId());
            sensorRepository.save(sensor);
        }
        return sensor;
    }

    private City saveCity(final SensorWeatherRecord record) {
        City city = cityRepository.findByName(record.getCityName());
        if (Objects.isNull(city)) {
            city = new City();
            city.setName(record.getCityName());
            cityRepository.save(city);
        }
        return city;
    }
}
