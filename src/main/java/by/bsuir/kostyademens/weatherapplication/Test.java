package by.bsuir.kostyademens.weatherapplication;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.service.OpenWeatherService;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        LocationDao locationDao = new LocationDao();
        OpenWeatherService openWeatherService = new OpenWeatherService();

//        openWeatherService.getLocationsByName("Minsk");

//        LocationDto locationDto = new LocationDto("Minsk", "BY", 53.9024716, 27.5618225);

//        openWeatherService.getWeatherForLocation(locationDto);
    }
}
