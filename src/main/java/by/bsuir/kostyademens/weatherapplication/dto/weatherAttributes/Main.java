package by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Main {

    @SerializedName("temp")
    private double temperature;

    public Long getTemperature() {
        return (long)temperature;
    }
}
