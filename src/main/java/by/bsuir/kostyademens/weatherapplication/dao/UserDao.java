package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import org.hibernate.Session;

public class UserDao {

  private final SessionFactoryUtil sessionFactoryUtil;

  public UserDao() {
    this.sessionFactoryUtil = SessionFactoryUtil.getInstance();
  }

  public void save(User user) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      session.persist(user);
      session.getTransaction().commit();
    }
  }

  public Optional<User> findByLogin(String email) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      User user =
          session
              .createQuery("FROM User u WHERE u.email = :email", User.class)
              .setParameter("email", email)
              .getSingleResult();
      session.getTransaction().commit();
      return Optional.of(user);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }
}
