package com.code.jam.weather.weatherrestassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class APITest {

	@Test()
	public void testGetWeatherForCity(String city) throws Exception {
		Response res = WeatherServiceCalls.getWeatherForCity("PUNE");
		String temp = res.getBody().jsonPath().get("temperature").toString();
		String rainfall = res.getBody().jsonPath().get("rainfall").toString();
		Assert.assertEquals(200, res.getStatusCode());
		System.out.println("temperature of pune is " + temp + "rain fall in mm of pune is " + rainfall);

	}

	@Test()
	public void testGetWeatherForSensor(String sensorName) throws Exception {
		Response res = WeatherServiceCalls.getWeatherForSensor("MUMBAI_5");
		String temp = res.getBody().jsonPath().get("temperature").toString();
		String rainfall = res.getBody().jsonPath().get("rainfall").toString();
		Assert.assertEquals(200, res.getStatusCode());
		System.out.println("temperature of MUMBAI_5 is " + temp + "rain fall in mm of MUMBAI_5 is " + rainfall);

	}

}
