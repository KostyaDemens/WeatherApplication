package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.api.WeatherApiResponse;

import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    private final LocationDao locationDao = new LocationDao();
    private final OpenWeatherService weatherService = new OpenWeatherService();

}
