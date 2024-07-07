package by.bsuir.kostyademens.weatherapplication.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationApiResponse {
    private String name;
    private String country;
    private BigDecimal lon;
    private BigDecimal lat;
}
