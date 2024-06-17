package by.bsuir.kostyademens.weatherapplication;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.service.OpenWeatherService;

public class Test {
    public static void main(String[] args) {
        LocationDao locationDao = new LocationDao();
        OpenWeatherService openWeatherService = new OpenWeatherService();

        openWeatherService.getLocationsByName("Minsk");
    }
}
