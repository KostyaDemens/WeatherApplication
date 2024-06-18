package by.bsuir.kostyademens.weatherapplication.model.apiLocationAttribute;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Wind {

    @SerializedName("speed")
    private Double windSpeed;
}
