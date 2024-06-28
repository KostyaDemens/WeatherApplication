package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import jakarta.persistence.NoResultException;

import java.util.Optional;
import java.util.UUID;

public class SessionDao {

    private final SessionFactoryUtil sessionFactoryUtil;

    public SessionDao() {
        this.sessionFactoryUtil = SessionFactoryUtil.getInstance();
    }

    public void save(Session entity) {
        try (org.hibernate.Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    public Session findById(String uuid) {
        try (org.hibernate.Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Session entity = session.createQuery("FROM Session s WHERE s.id = :uuid", Session.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
            session.getTransaction().commit();
            return entity;
        } catch (NoResultException e) {
            return null;
            //TODO change logic
        }
    }
}
