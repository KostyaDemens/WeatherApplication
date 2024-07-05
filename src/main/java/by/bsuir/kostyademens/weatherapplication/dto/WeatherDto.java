package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private List<Weather> weather;
    private Coordinates coord;
    private Main main;
    private Sys sys;
    private String name;
    private boolean hasLocation;

}
