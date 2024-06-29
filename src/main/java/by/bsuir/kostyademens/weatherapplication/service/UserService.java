package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserService {
    private LocationDao locationDao = new LocationDao();

//    public boolean isUserHasLocation(User user, Location locationModel) {
//        List<Location> userLocations = locationDao.findUserLocations(user);
//        for (Location location : userLocations) {
//            if (location.getLatitude().compareTo(locationModel.getLatitude()) == 0 &&
//                    location.getLongitude().compareTo(locationModel.getLongitude()) == 0) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean isUserHasLocation(User user, Location locationModel) {
        List<Location> userLocations = locationDao.findUserLocations(user);
        int i = 2;
        return userLocations.contains(locationModel);
    }


}
