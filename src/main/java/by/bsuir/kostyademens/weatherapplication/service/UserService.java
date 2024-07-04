package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final LocationDao locationDao = new LocationDao();

    public boolean isUserHasLocation(User user, WeatherDto weatherDto) {
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
