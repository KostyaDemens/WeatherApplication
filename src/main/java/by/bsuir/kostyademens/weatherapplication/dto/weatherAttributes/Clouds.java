package by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Clouds {

    @SerializedName("all")
    private Double cloudsPercentage;
}
