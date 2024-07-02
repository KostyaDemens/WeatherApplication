package by.bsuir.kostyademens.weatherapplication.mapper;

import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.math.BigDecimal;

public class LocationMapper {
    private final ModelMapper modelMapper;

    public LocationMapper() {
        this.modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<LocationDto, Location>() {
            @Override
            protected void configure() {
                map().setName(source.getName());
                map().setLatitude(source.getLat());
                map().setLongitude(source.getLon());
            }
        });
    }

    public Location convertToModel(LocationDto locationDto) {
        return modelMapper.map(locationDto, Location.class);
    }

    public LocationDto convertToDto(Location location) {
        return modelMapper.map(location, LocationDto.class);
    }
}
