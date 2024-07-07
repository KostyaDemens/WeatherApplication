package by.bsuir.kostyademens.weatherapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationDto {
    private boolean hasLocation;
    private String country;
    private String name;
    private WeatherDto weather;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
