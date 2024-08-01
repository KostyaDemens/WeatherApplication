package by.bsuir.kostyademens.weatherapplication.service;

import static by.bsuir.kostyademens.weatherapplication.util.PropertyReader.fromFile;

import by.bsuir.kostyademens.weatherapplication.api.LocationApiResponse;
import by.bsuir.kostyademens.weatherapplication.api.WeatherApiResponse;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.exception.NoSuchCountryException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OpenWeatherService {

    private final String API_KEY = fromFile("application.properties").getProperty("API_KEY");
  private final String WEATHER_API_URL = "https://api.openweathermap.org/";
  private final ObjectMapper objectMapper = new ObjectMapper();

  public List<LocationDto> getLocationsByName(String locationName) {
    List<LocationDto> locationDtos = new ArrayList<>();
    try {
      String urlString =
          WEATHER_API_URL + "geo/1.0/direct?q=" + locationName + "&limit=5&appid=" + API_KEY;
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      List<LocationApiResponse> locations =
          objectMapper.readValue(readJson(connection), new TypeReference<>() {});

      for (LocationApiResponse location : locations) {
        LocationDto locationDto =
            LocationDto.builder()
                .name(location.getName())
                .latitude(location.getLat())
                .longitude(location.getLon())
                .build();
        locationDtos.add(locationDto);
      }

      if (locations.isEmpty()) {
        throw new NoSuchCountryException(
            "Error: The location you are searching for does not exist");
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return locationDtos;
  }

  public WeatherDto getWeatherForecast(BigDecimal latitude, BigDecimal longitude) {
    try {

      String urlString = String.format(
              "%sdata/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric",
              WEATHER_API_URL,
              latitude,
              longitude,
              API_KEY
      );

      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");


      WeatherApiResponse weatherApiResponse;
      ObjectMapper objectMapper = new ObjectMapper();

      try (Reader reader = new InputStreamReader(connection.getInputStream())) {
        weatherApiResponse = objectMapper.readValue(reader, WeatherApiResponse.class);
      } catch (Exception jsonExc) {
        throw new RuntimeException(jsonExc);
      }

      return WeatherDto.builder()
          .description(weatherApiResponse.getWeather().get(0).getDescription())
          .iconName(weatherApiResponse.getWeather().get(0).getMain())
          .temperature(weatherApiResponse.getMain().getTemperature())
          .country(weatherApiResponse.getSys().getCountry())
          .build();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String readJson(HttpURLConnection connection) throws IOException {
    StringBuilder result = new StringBuilder();
    try (BufferedReader bufferedReader =
        new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        result.append(line);
      }
      return result.toString();
    }
  }
}
