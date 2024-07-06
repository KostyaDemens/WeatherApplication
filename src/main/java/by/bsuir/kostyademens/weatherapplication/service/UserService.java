package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;

import java.util.List;

public class UserService {
    private final LocationDao locationDao = new LocationDao();

    public boolean isUserHasForecast(User user, WeatherDto weatherDto) {
        List<Location> userLocations = locationDao.findUserLocations(user);
        for (Location location : userLocations) {
            if (location.getLatitude().compareTo(weatherDto.getCoord().getLatitude()) == 0 &&
                    location.getLongitude().compareTo(weatherDto.getCoord().getLongitude()) == 0) {
                return true;
            }
        }
        return false;
    }


}
