package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.model.apiLocationAttribute.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WeatherDto {
    private Coordinates coordinates;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private String name;

}
