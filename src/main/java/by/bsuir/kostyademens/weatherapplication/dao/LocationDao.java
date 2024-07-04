package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public Location findById(Long id) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Location location = session.createQuery("FROM Location l WHERE l.id = :id", Location.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return location;
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

    public void deleteByCoordinates(BigDecimal longitude, BigDecimal latitude, User user) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Location location = session.createQuery("FROM Location l WHERE l.longitude = :longitude AND l.latitude = :latitude AND l.user = :user", Location.class)
                            .setParameter("longitude", longitude)
                                    .setParameter("latitude", latitude)
                    .setParameter("user", user)
                                            .getSingleResult();
            session.remove(location);
            session.getTransaction().commit();
        }
    }

    public Optional<Location> findByCoordinates(BigDecimal longitude, BigDecimal latitude) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Location location = session.createQuery("FROM Location l WHERE l.longitude = :longitude AND l.latitude = :latitude", Location.class)
                    .setParameter("longitude", longitude)
                    .setParameter("latitude", latitude)
                    .getSingleResult();
            session.getTransaction().commit();
            return Optional.of(location);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
