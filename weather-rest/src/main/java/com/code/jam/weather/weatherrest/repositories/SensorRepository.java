package com.code.jam.weather.weatherrest.repositories;

import com.code.jam.weather.weatherrest.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
