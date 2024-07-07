package by.bsuir.kostyademens.weatherapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@Builder
public class CoordinatesDto {
    private BigDecimal lat;
    private BigDecimal lon;
}
