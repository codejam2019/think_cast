package com.code.jam.weather.weatherrest.repositories;

import com.code.jam.weather.weatherrest.entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
