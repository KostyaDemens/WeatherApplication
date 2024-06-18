package by.bsuir.kostyademens.weatherapplication.mapper;

import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.LocationApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class LocationMapper {

    private ModelMapper modelMapper = new ModelMapper();

    PropertyMap<LocationApiResponse, WeatherDto> locationMap = new PropertyMap<LocationApiResponse, WeatherDto>() {
        @Override
        protected void configure() {
            map().setSys(source.getSys());
            map().setName(source.getName());
        }
    };



}
