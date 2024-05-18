package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import org.hibernate.Session;

public class UserDao {

    private final SessionFactoryUtil sessionFactoryUtil;

    public UserDao(SessionFactoryUtil sessionFactoryUtil) {
        this.sessionFactoryUtil = sessionFactoryUtil;
        sessionFactoryUtil.init();
    }

    public void save(User user) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }
}
