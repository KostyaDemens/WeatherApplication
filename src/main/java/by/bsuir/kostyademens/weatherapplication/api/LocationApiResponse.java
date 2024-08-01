package by.bsuir.kostyademens.weatherapplication.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationApiResponse {
  @JsonProperty("name")
  private String name;
  @JsonProperty("country")
  private String country;

  @JsonProperty("lon")
  private BigDecimal lon;
  @JsonProperty("lat")
  private BigDecimal lat;
}
