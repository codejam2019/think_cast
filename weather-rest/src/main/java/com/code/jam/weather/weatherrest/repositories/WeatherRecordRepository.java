package com.code.jam.weather.weatherrest.repositories;

import com.code.jam.weather.weatherrest.entities.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {

    List<WeatherRecord> findBySensorId(Long sensorId);

}
