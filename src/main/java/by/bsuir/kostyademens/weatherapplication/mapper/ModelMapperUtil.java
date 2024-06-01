package by.bsuir.kostyademens.weatherapplication.mapper;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtil {
    @Getter
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


}
