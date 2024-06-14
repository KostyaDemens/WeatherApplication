package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.LocationDao;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherServiceTest {

    @InjectMocks
    private OpenWeatherService openWeatherService;

    @Mock
    private LocationDao locationDao;

    private Location location;

    @BeforeEach
    void prepare() {
        location = new Location("London", BigDecimal.valueOf(51.5085), BigDecimal.valueOf(-0.1257));
    }

    @Test
    void saveLocation_ShouldSaveLocationInDataBase() {


    }
}
