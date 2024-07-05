package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;

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

    public List<WeatherDto> getUserForecasts(User user) {
        List<WeatherDto> weatherForecasts = new ArrayList<>();
        List<Location> locations = locationDao.findUserLocations(user);
        for (Location location : locations) {
            WeatherDto weatherForecast = weatherService.getWeatherForecast(location.getLatitude(), location.getLongitude());
            weatherForecast.setHasLocation(true);
            weatherForecast.setName(location.getName());
            weatherForecasts.add(weatherForecast);
        }
            return weatherForecasts;
    }

    public List<WeatherDto> getForecasts(String locationName) {
        List<LocationDto> locations = weatherService.getLocationsByName(locationName);
        List<WeatherDto> forecasts = new ArrayList<>();
        for (LocationDto location : locations) {
            WeatherDto weatherDto = weatherService.getWeatherForecast(location.getLat(), location.getLon());
            weatherDto.setName(location.getName());
            weatherDto.getSys().setCountry(location.getCountry());
            forecasts.add(weatherDto);
        }
        return forecasts;
    }

    public void saveLocation(Location location) {
        locationDao.save(location);
    }

    public void deleteLocation(BigDecimal longitude, BigDecimal latitude, User user) {
        locationDao.deleteByCoordinates(longitude, latitude, user);
    }
}
