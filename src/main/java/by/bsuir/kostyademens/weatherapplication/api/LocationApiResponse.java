package by.bsuir.kostyademens.weatherapplication.api;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationApiResponse {
  private String name;
  private String country;
  private BigDecimal lon;
  private BigDecimal lat;
}
