package by.bsuir.kostyademens.weatherapplication;

import by.bsuir.kostyademens.weatherapplication.service.OpenWeatherService;

public class Test {
    public static void main(String[] args) {
        OpenWeatherService openWeatherService = new OpenWeatherService();

        openWeatherService.getLocationByName("London");
    }
}
