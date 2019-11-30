package com.code.jam.weather.weatherrestassured;

public interface InterfaceWeatherTest {

	// Keeping one static method in Interface so that its implemented class needs not to override and can use directly: Java 8 Concepts
	public static String buildServiceURL() {
		return  new FileUtilities().getPropertyValue("URL");
	}
}