package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.CoordinatesDto;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;

import java.util.List;

public class LocationService {

    private final LocationDao locationDao = new LocationDao();
    private final OpenWeatherService weatherService = new OpenWeatherService();

    public List<LocationDto> findLocationsByName(String locationName) {
        List<LocationDto> locations = weatherService.getLocationsByName(locationName);
        for (LocationDto location : locations) {
            location.setWeather(weatherService.getWeatherForecast(location.getLatitude(), location.getLongitude()));
        }

        return locations;
    }

    public void save(String name, CoordinatesDto coordinates, User user) {
        Location location = Location.builder()
                .latitude(coordinates.getLat())
                .longitude(coordinates.getLon())
                .name(name)
                .user(user)
                .build();
        locationDao.save(location);
    }



    public void delete(CoordinatesDto coordinates, User user) {
        Location location = Location.builder()
                .latitude(coordinates.getLat())
                .longitude(coordinates.getLon())
                .user(user)
                .build();
        locationDao.delete(location);
    }

}
