package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.LocationApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherService {

    private LocationDao locationDao;

    private final String API_KEY = "3725ced7f88e411534bfa17a8f93d01a";

    private final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public void getLocationByName(String locationName) {
        try {
            StringBuilder result = new StringBuilder();
            String urlString = WEATHER_API_URL + "?q=" + locationName + "&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            readJson(connection, result);

            Gson gson = new GsonBuilder().create();
            LocationApiResponse locationApiResponse = gson.fromJson(String.valueOf(result), LocationApiResponse.class);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
