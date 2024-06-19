package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WeatherDto {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private String name;

}
