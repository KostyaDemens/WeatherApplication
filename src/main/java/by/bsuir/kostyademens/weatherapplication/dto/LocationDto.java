package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes.Coordinates;
import by.bsuir.kostyademens.weatherapplication.model.User;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private boolean hasLocation;
    private String name;
    private String country;
    private BigDecimal lat;
    private BigDecimal lon;
    private WeatherDto weatherDto;
}
