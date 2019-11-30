package com.code.jam.wather.weatherrest.repositories;

import com.code.jam.wather.weatherrest.entities.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
