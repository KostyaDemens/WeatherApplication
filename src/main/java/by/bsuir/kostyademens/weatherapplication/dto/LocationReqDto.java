package by.bsuir.kostyademens.weatherapplication.dto;

import by.bsuir.kostyademens.weatherapplication.dto.dtoAttribute.Sys;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LocationReqDto {

    private Sys sys;
    private String name;
}
