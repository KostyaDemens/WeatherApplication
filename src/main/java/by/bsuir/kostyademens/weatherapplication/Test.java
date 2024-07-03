package by.bsuir.kostyademens.weatherapplication;


import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.service.LocationService;
import by.bsuir.kostyademens.weatherapplication.service.OpenWeatherService;
import by.bsuir.kostyademens.weatherapplication.service.UserService;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        OpenWeatherService weatherService = new OpenWeatherService();
        LocationService locationService = new LocationService();
        LocationDao locationDao = new LocationDao();

//        Optional<Location> location = locationDao.findByCoordinates(BigDecimal.valueOf(37.6172), BigDecimal.valueOf(55.7558));
String longitude = "37.6172";
String latitude = "55.7558";
        locationDao.deleteByCoordinates(new BigDecimal(longitude), new BigDecimal(latitude));

        int i = 2;

        WeatherDto weatherDto = weatherService.getWeatherForecast(BigDecimal.valueOf(53.9024716), BigDecimal.valueOf(27.5618225));


    }
}
