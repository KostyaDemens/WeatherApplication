package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LocationService {

    private final LocationDao locationDao = new LocationDao();
    public Location findLocationByCoordinates(BigDecimal longitude, BigDecimal latitude) {
        return locationDao.findByCoordinates(longitude, latitude).get();
    }

    public List<Location> findUserLocations(User user) {
        return locationDao.findUserLocations(user);
    }
}
