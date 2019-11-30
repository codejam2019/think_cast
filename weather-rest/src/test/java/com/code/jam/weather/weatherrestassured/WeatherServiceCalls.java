package com.code.jam.weather.weatherrestassured;

import static com.code.jam.weather.weatherrestassured.Constants.BASE_URL;
import static com.code.jam.weather.weatherrestassured.Constants.REQUESTSPECIFICATION;
import static io.restassured.RestAssured.given;

import org.json.JSONException;

import io.restassured.response.Response;

public class WeatherServiceCalls {

	public static Response getWeatherForCity(String cityName) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("city", cityName).when().get(BASE_URL);
	}

	public static Response getWeatherForSensor(String sensorName) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("sensorName", sensorName).when().get(BASE_URL);
	}

}
