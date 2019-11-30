package com.code.jam.weather.weatherrest.controllers;

import com.code.jam.weather.weatherrest.controllers.model.WeatherRecord;
import com.code.jam.weather.weatherrest.repositories.SampleRepository;
import com.code.jam.weather.weatherrest.services.SensorWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SampleRepository sampleRepository;

    @MockBean
    private SensorWeatherService sensorWeatherService;


    @Test
    public void shouldSaveRecord() throws Exception {
        final String city = "Pune";
        final String sensorName = "Pune_01";
        final WeatherRecord record = new WeatherRecord();
        record.setRainFall(100L);
        record.setTemperature(12.23F);
        record.setTimestamp(new Date());

        final MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cities/" + city + "/sensors/" + sensorName)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json(record));
        mvc.perform(requestBuilder)
            .andExpect(status().isOk());

    }

    private <T> String json(final T record) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(record);
    }
}
