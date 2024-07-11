package by.bsuir.kostyademens.weatherapplication.api.weatherApiAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Weather {
  private String description;
  private String main;

  public String getMain() {
    return main.toUpperCase();
  }
}
