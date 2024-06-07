package by.bsuir.kostyademens.weatherapplication.service.service;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UserDao userDao;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



}