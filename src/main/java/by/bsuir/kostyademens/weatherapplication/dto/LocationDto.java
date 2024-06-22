package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes.Coordinates;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private String name;
    private String country;
    private Double lat;
    private Double lon;
    private WeatherDto weatherDto;
}
