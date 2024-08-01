package by.bsuir.kostyademens.weatherapplication.integrationTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.exception.NoSuchCountryException;
import by.bsuir.kostyademens.weatherapplication.service.OpenWeatherService;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

public class OpenWeatherServiceIntegrationTest {

  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private OpenWeatherService openWeatherService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getLocationsByNameShouldReturnCorrectNumberOfCountries() {
    String locationName = "Minsk";

    List<LocationDto> locations = openWeatherService.getLocationsByName(locationName);

    assertNotNull(locations);
    assertEquals(2, locations.size());
  }

  @Test
  public void getLocationByNameShouldThrowNoSuchCountryException() throws IOException {
    String locationName = "UnknownPlace";

    when(objectMapper.readValue(Mockito.anyString(), Mockito.eq(new TypeReference<>() {}))).thenReturn(null);

    assertThrows(NoSuchCountryException.class, () -> openWeatherService.getLocationsByName(locationName));
  }
}
