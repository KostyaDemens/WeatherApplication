package by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Weather {
    private Long id;
    private String description;
    private String main;

    public String getMain() {
        return main.toUpperCase();
    }
}
