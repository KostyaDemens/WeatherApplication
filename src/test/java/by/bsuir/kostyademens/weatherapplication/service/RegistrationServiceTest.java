package by.bsuir.kostyademens.weatherapplication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.exception.UserAlreadyExistsException;
import by.bsuir.kostyademens.weatherapplication.model.User;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

  private static final User IVAN = new User("Ivan", "123");
  @Mock private UserDao userDao;
  @InjectMocks private RegistrationService registrationService;

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
