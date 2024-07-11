package by.bsuir.kostyademens.weatherapplication.integrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.exception.UserAlreadyExistsException;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.service.RegistrationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

public class RegistrationServiceIntegrationTest {

  private static SessionFactory sessionFactory;
  private User IVAN;
  private RegistrationService registrationService;

  @AfterAll
  static void tearDown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }

  @BeforeEach
  void setUp() {
    IVAN = new User("kostyademens@gmail.com", "123");
    UserDao userDao = new UserDao();
    registrationService = new RegistrationService(userDao);

    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    sessionFactory = configuration.buildSessionFactory();
  }

  @Test
  @Order(1)
  void userShouldBeSavedInDataBase() {
    registrationService.register(IVAN);

    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      User savedUser = session.get(User.class, IVAN.getId());

      assertNotNull(savedUser);
      assertEquals(IVAN.getEmail(), savedUser.getEmail());

      session.getTransaction().commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @Order(2)
  void shouldThrowAnExceptionIfUserAlreadyExists() {
    registrationService.createNewUser(IVAN);

    Assertions.assertThrows(
        UserAlreadyExistsException.class, () -> registrationService.register(IVAN));
  }
}
