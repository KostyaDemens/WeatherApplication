package by.bsuir.kostyademens.weatherapplication.api;

import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Coordinates;
import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Main;
import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Sys;
import by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiResponse {
    private List<Weather> weather;
    private Coordinates coord;
    private Main main;
    private Sys sys;
}
