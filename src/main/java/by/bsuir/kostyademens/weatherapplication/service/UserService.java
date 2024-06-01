package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.model.User;

import javax.mail.internet.InternetAddress;

public class UserService {
    private final UserDao userDao = new UserDao();

    public void createNewUser(User user) {
        userDao.save(user);
    }

    public boolean isUserExists(String email) {
        return userDao.findByLogin(email).isPresent();
    }

    public boolean isValidEmail(String email) {
        try {
            new InternetAddress(email).validate();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
