package by.bsuir.kostyademens.weatherapplication.model;

import by.bsuir.kostyademens.weatherapplication.model.apiModelAttributes.*;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LocationApiResponse {

    @SerializedName("coord")
    private Coordinates coordinates;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
}
