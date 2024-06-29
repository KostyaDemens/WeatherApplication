package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LocationDao {

    private final SessionFactoryUtil sessionFactoryUtil;

    public LocationDao() {
        this.sessionFactoryUtil = SessionFactoryUtil.getInstance();
    }

    public void save(Location location) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            session.persist(location);
            session.getTransaction().commit();
        }
    }

    public Optional<Location> findByName(String name) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Location location = session.createQuery("FROM Location l WHERE l.name = :name", Location.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(location);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<Location> findUserLocations(User user) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            List<Location> location = session.createQuery("FROM Location l WHERE l.user = :user", Location.class)
                    .setParameter("user", user)
                    .getResultList();
            session.getTransaction().commit();
            return location;
        }
    }
}
