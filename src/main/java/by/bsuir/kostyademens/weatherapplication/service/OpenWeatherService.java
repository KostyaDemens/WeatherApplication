package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.exception.NoSuchCountryException;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherService {

    private LocationDao locationDao;

    private final String API_KEY = "3725ced7f88e411534bfa17a8f93d01a";

    private final String WEATHER_API_URL = "https://api.openweathermap.org/";

    public List<LocationDto> getLocationsByName(String locationName) {
        List<LocationDto> locations = new ArrayList<>();
        try {
            StringBuilder result = new StringBuilder();
            String urlString = WEATHER_API_URL + "geo/1.0/direct?q=" + locationName + "&limit=5&appid=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            readJson(connection, result);

            JsonArray jsonArray = JsonParser.parseString(result.toString()).getAsJsonArray();

            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String name = jsonObject.get("name").getAsString();
                String country = jsonObject.get("country").getAsString();
                locations.add(new LocationDto(name, country));
            }

            if (locations.isEmpty()) {
                throw new NoSuchCountryException("Error: The location you are searching for does not exist");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }


    private void readJson(HttpURLConnection connection, StringBuilder result) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
    }

    private void saveLocation(Location location) {
        locationDao.save(location);
    }
}
