package by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Main {

    @SerializedName("temp")
    private double temperature;

    @SerializedName("feels_like")
    private double feelsLike;

    private Double humidity;

    private Double pressure;

    public Long getTemperature() {
        return (long)temperature;
    }

    public Long getFeelsLikeTemperature() {
        return (long)feelsLike;
    }
}
