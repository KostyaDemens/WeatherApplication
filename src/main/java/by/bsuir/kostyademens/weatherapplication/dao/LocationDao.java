package by.bsuir.kostyademens.weatherapplication.dao;

import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.util.SessionFactoryUtil;
import org.hibernate.Session;

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
}
