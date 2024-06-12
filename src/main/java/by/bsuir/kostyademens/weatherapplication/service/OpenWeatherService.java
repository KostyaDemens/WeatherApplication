package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.model.Location;

public class OpenWeatherService {

    private final String API_KEY = "3725ced7f88e411534bfa17a8f93d01a";

    private String url;

    public Location getLocationByName(String name) {
        return new Location();
    }
}
