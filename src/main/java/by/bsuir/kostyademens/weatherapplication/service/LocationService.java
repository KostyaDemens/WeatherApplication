package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.mapper.LocationMapper;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LocationService {

    private final LocationDao locationDao = new LocationDao();
    private final OpenWeatherService weatherService = new OpenWeatherService();


    public Location findLocationByCoordinates(BigDecimal longitude, BigDecimal latitude) {
        return locationDao.findByCoordinates(longitude, latitude)
                .orElseThrow(() -> new NoSuchElementException("Location not found for the given coordinates"));
    }

    public List<WeatherDto> getUserForecasts(User user) {
        List<WeatherDto> weatherForecasts = new ArrayList<>();
        List<Location> locations = locationDao.findUserLocations(user);
        for (Location location : locations) {
            WeatherDto weatherForecast = weatherService.getWeatherForecast(location.getLatitude(), location.getLongitude());
            weatherForecasts.add(weatherForecast);
        }
            return weatherForecasts;
    }

    public void saveLocation(Location location) {
        locationDao.save(location);
    }

    public void deleteLocation(BigDecimal longitude, BigDecimal latitude) {
        locationDao.deleteByCoordinates(longitude, latitude);
    }
}
