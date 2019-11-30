package com.code.jam.weather.weatherrest.controllers;

import com.code.jam.weather.weatherrest.controllers.model.Sample;
import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;
import com.code.jam.weather.weatherrest.controllers.model.WeatherRecord;
import com.code.jam.weather.weatherrest.repositories.SampleRepository;
import com.code.jam.weather.weatherrest.repositories.WeatherRecordRepository;
import com.code.jam.weather.weatherrest.services.SensorWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherController {

    private final SampleRepository sampleRepository;
    private final SensorWeatherService sensorWeatherService;
    private final WeatherRecordRepository weatherRecordRepository;

    @Autowired
    public WeatherController(final SampleRepository sampleRepository,
                             final SensorWeatherService sensorWeatherService, final WeatherRecordRepository weatherRecordRepository) {
        this.sampleRepository = sampleRepository;
        this.sensorWeatherService = sensorWeatherService;
        this.weatherRecordRepository = weatherRecordRepository;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/samples")
    public List<Sample> getSample() {
        return sampleRepository.findAll()
                .stream()
                .map(s -> new Sample(s.getName(), s.getAge()))
                .collect(Collectors.toList());
    }

    @PostMapping("/cities/{cityName}/sensors/{sensorName}")
    public void saveRecord(@PathVariable("cityName") final String cityName,
                           @PathVariable("sensorName") final String sensorName,
                           @RequestBody WeatherRecord record) {
        final SensorWeatherRecord weatherRecord = new SensorWeatherRecord();
        weatherRecord.setRainFall(record.getRainFall());
        weatherRecord.setTemperature(record.getTemperature());
        weatherRecord.setTimestamp(record.getTimestamp());
        weatherRecord.setCityName(cityName);
        weatherRecord.setSensorName(sensorName);
        sensorWeatherService.save(weatherRecord);
    }

    @GetMapping("/records/cities/{cityName}")
    public List<WeatherRecord> recordsForCity(@PathVariable("cityName")final String city,
                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date from,
                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date to) {
        return sensorWeatherService.findForCity(city, from, to);
    }

    @GetMapping("/records/sensors/{sensorName}")
    public List<WeatherRecord> recordsForSensor(@PathVariable("sensorName")final String sensor,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date from,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date to) {
        return sensorWeatherService.findForSensor(sensor, from, to);
    }

    @GetMapping("/cities/hottest/city")
    public String hottestCity(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date from,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") final Date to) {
        return sensorWeatherService.findHottestCity(from, to);
    }

}
