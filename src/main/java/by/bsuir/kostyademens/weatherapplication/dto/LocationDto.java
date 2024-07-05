package by.bsuir.kostyademens.weatherapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private String name;
    private String country;
    private BigDecimal lat;
    private BigDecimal lon;
}
