package by.bsuir.kostyademens.weatherapplication.model.apiModelAttributes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class Sys {

    private Double sunrise;
    private Double sunset;
}
