package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.SessionDao;
import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.http.Cookie;

import java.time.LocalDateTime;

public class AuthorizationService {
    private final SessionDao sessionDao = new SessionDao();

    public Session login(User user) {

            Session session = getNewSession(user);
            sessionDao.save(session);

        return session;
    }

    public Cookie getNewCookie(Session session) {
        Cookie cookie = new Cookie("session_id", session.getId());
        cookie.setMaxAge(5 * 60 * 60);
        return cookie;
    }

    public Session getNewSession(User user) {
        Session session = new Session();
        session.setUser(user);
        session.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        return session;
    }
}
