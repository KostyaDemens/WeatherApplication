package by.bsuir.kostyademens.weatherapplication.dto.weatherAttributes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Coordinates {

    @SerializedName("lon")
    private BigDecimal longitude;

    @SerializedName("lat")
    private BigDecimal latitude;
}
