package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.exception.NoSuchCountryException;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherService {

    private LocationDao locationDao = new LocationDao();
    private final String API_KEY = "3725ced7f88e411534bfa17a8f93d01a";
    private final String WEATHER_API_URL = "https://api.openweathermap.org/";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<LocationDto> getLocationsByName(String locationName) {
        List<LocationDto> locations;
        try {
            StringBuilder result = new StringBuilder();
            String urlString = WEATHER_API_URL + "geo/1.0/direct?q=" + locationName + "&limit=5&appid=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            readJson(connection, result);

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


           locations = objectMapper.readValue(result.toString(), new TypeReference<>() {});

            Set<String> countries = new HashSet<>();

            locations.removeIf(location -> !countries.add(location.getCountry()));


            if (locations.isEmpty()) {
                throw new NoSuchCountryException("Error: The location you are searching for does not exist");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return locations;


    }

    public WeatherDto getWeatherForLocation(LocationDto locationDto) throws IOException {
        StringBuilder result = new StringBuilder();
        String urlString = WEATHER_API_URL + "/data/2.5/weather?lat=" + locationDto.getLat() + "&lon=" + locationDto.getLon() + "&appid=" + API_KEY + "&units=metric";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        readJson(connection, result);

        Gson gson = new GsonBuilder().create();
        return gson.fromJson(result.toString(), WeatherDto.class);
    }


    private void readJson(HttpURLConnection connection, StringBuilder result) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
    }

    public void saveLocation(Location location) {
        locationDao.save(location);
    }
}
