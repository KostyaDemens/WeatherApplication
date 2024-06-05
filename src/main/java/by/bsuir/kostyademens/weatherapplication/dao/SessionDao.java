package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;

public class SessionDao {

    private final SessionFactoryUtil sessionFactoryUtil;

    public SessionDao() {
        this.sessionFactoryUtil = SessionFactoryUtil.getInstance();
    }

    public void save(Session entity) {
        try (org.hibernate.Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            session.persist(session);
            session.getTransaction().commit();
        }
    }
}
