package by.bsuir.kostyademens.weatherapplication.model.apiLocationAttribute;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Main {

    @SerializedName("temp")
    private Double temperature;

    @SerializedName("temp_min")
    private Double minTemperature;

    @SerializedName("temp_max")
    private Double maxTemperature;

    @SerializedName("humidity")
    private Double humidityPercentage;

    private Double pressure;
}
