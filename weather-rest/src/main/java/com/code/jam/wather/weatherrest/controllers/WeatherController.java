package com.code.jam.wather.weatherrest.controllers;

import com.code.jam.wather.weatherrest.controllers.model.Sample;
import com.code.jam.wather.weatherrest.repositories.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherController {

    private final SampleRepository sampleRepository;

    @Autowired
    public WeatherController(final SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
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

    @GetMapping("/samples/{id}")
    public Sample getSample(@PathVariable final Long id) {
        final com.code.jam.wather.weatherrest.entities.Sample entity = sampleRepository.getOne(id);
        return new Sample(entity.getName(), entity.getAge());
    }

}
