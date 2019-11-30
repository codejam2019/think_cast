package com.code.jam.weather.weatherrest.repositories;

import com.code.jam.weather.weatherrest.entities.City;
import com.code.jam.weather.weatherrest.entities.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {

    List<WeatherRecord> findBySensorId(Long sensorId);

    @Query("SELECT r from WeatherRecord r " +
            " JOIN Sensor s on r.sensorId = s.id " +
            " JOIN City c on c.id = s.cityId" +
            " WHERE c.name = :name")
    List<WeatherRecord> findByCity(@Param("name") final String name);

    @Query("SELECT r from WeatherRecord r " +
            " JOIN Sensor s on r.sensorId = s.id " +
            " JOIN City c on c.id = s.cityId" +
            " WHERE c.name = :name " +
            " AND r.timestamp >= :fromDate AND r.timestamp <= :toDate")
    List<WeatherRecord> findByCity(@Param("name") final String name,
                                   @Param("fromDate") final Date fromDate,
                                   @Param("toDate") final Date toDate);

    @Query("SELECT r from WeatherRecord r " +
            " JOIN Sensor s on r.sensorId = s.id " +
            " WHERE s.name = :name")
    List<WeatherRecord> findBySensor(@Param("name") final String name);

    @Query("SELECT r from WeatherRecord r " +
            " JOIN Sensor s on r.sensorId = s.id " +
            " WHERE s.name = :name " +
            " AND r.timestamp >= :fromDate AND r.timestamp <= :toDate")
    List<WeatherRecord> findBySensor(@Param("name") final String name,
                                   @Param("fromDate") final Date fromDate,
                                   @Param("toDate") final Date toDate);

}
