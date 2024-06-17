package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.dto.dtoAttribute.*;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class LocationRespDto {

    @SerializedName("coord")
    private Coordinates coordinates;
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;
    private String name;
}
