package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.exception.UserAlreadyExistsException;
import by.bsuir.kostyademens.weatherapplication.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private RegistrationService registrationService;

    private static final User IVAN = new User("Ivan", "123");


    @Test
    void shouldThrowAnExceptionIfUserAlreadyExists() {
        when(userDao.findByLogin(IVAN.getEmail())).thenReturn(Optional.of(IVAN));

        assertThrows(UserAlreadyExistsException.class, () -> registrationService.register(IVAN));
    }

    @Test
    void shouldSaveUserIfUserDoesNotExists() {
        when(userDao.findByLogin(IVAN.getEmail())).thenReturn(Optional.empty());

        registrationService.register(IVAN);

        verify(userDao, times(1)).save(IVAN);
    }

}
