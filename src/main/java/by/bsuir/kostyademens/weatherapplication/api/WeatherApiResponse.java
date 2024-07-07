package by.bsuir.kostyademens.weatherapplication.api;

import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Main;
import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Sys;
import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Weather;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiResponse {
  private List<Weather> weather;
  private Main main;
  private Sys sys;
}
