package by.bsuir.kostyademens.weatherapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class CoordinatesDto {
    private BigDecimal lat;
    private BigDecimal lon;
}
