package by.bsuir.kostyademens.weatherapplication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.exception.UserAlreadyExistsException;
import by.bsuir.kostyademens.weatherapplication.model.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UserDao userDao;


    private User user;

    @BeforeEach
    void prepare() {
        user = new User("Vova", "123");
    }

    @Test
    void registerUser_ShouldThrowUserAlreadyExistsException() {
        when(userDao.findByLogin(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> registrationService.register(user));
    }

    @Test
    void registerUser_ShouldCreateNewUserSuccessfully() {
        when(userDao.findByLogin(user.getEmail())).thenReturn(Optional.empty());

        registrationService.register(user);

        verify(userDao, times(1)).save(user);
    }


}