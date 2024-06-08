package by.bsuir.kostyademens.weatherapplication.service.service;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.model.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import by.bsuir.kostyademens.weatherapplication.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UserDao userDao;


    @Test
    void createNewUser_ShouldInvokeSaveOnUserDao() {
        User user = new User("Vova", "123");

        // Выполнение метода createNewUser
        registrationService.createNewUser(user);

        //Проверка. Метод был вызван один раз с параметром user
        verify(userDao, times(1)).save(user);
    }



}