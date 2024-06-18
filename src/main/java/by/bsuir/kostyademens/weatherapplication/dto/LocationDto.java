package by.bsuir.kostyademens.weatherapplication.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LocationDto {

    @SerializedName("name")
    private String name;
    private String country;
}
