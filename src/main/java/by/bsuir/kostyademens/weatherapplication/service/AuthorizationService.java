package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.SessionDao;
import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.dto.UserDto;
import by.bsuir.kostyademens.weatherapplication.exception.AuthorizationException;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.validator.PasswordValidator;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
public class AuthorizationService {

    private final SessionDao sessionDao;
    private final UserDao userDao;

    public AuthorizationService() {
        this.sessionDao = new SessionDao();
        this.userDao = new UserDao();
    }

    public Session login(UserDto userDto) {
        Optional<User> user = userDao.findByLogin(userDto.getEmail());
        if (user.isPresent()) {
            if (PasswordValidator.checkPassword(userDto.getPassword(), user.get().getPassword())) {
                Session session = getNewSession(user.get());
                sessionDao.save(session);
                return session;
            } else {
                throw new AuthorizationException("Invalid username or password");
            }
        } else {
            throw new AuthorizationException("Invalid username or password");
        }
    }

    public Cookie getNewCookie(Session session) {
        Cookie cookie = new Cookie("session_id", session.getId());
        cookie.setMaxAge(5 * 60 * 60);
        return cookie;
    }

    public Session getNewSession(User user) {
        Session session = new Session();
        session.setUser(user);
        session.setExpiresAt(LocalDateTime.now().plusMinutes(1));
        return session;
    }

    public Session getSession(String uuid) {
        return sessionDao.findById(uuid);
    }
}
