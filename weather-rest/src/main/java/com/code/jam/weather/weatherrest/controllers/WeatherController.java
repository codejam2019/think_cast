package com.code.jam.weather.weatherrest.controllers;

import com.code.jam.weather.weatherrest.controllers.model.Sample;
import com.code.jam.weather.weatherrest.controllers.model.SensorWeatherRecord;
import com.code.jam.weather.weatherrest.repositories.SampleRepository;
import com.code.jam.weather.weatherrest.services.SensorWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherController {

    private final SampleRepository sampleRepository;
    private final SensorWeatherService sensorWeatherService;

    @Autowired
    public WeatherController(final SampleRepository sampleRepository,
                             final SensorWeatherService sensorWeatherService) {
        this.sampleRepository = sampleRepository;
        this.sensorWeatherService = sensorWeatherService;
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
                           @RequestBody SensorWeatherRecord record) {
        sensorWeatherService.save(record);
    }

    @GetMapping("/samples/{id}")
    public Sample getSample(@PathVariable final Long id) {
        final com.code.jam.weather.weatherrest.entities.Sample entity = sampleRepository.getOne(id);
        return new Sample(entity.getName(), entity.getAge());
    }

}
