package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.dto.UserDto;
import by.bsuir.kostyademens.weatherapplication.model.User;

import javax.mail.internet.InternetAddress;

public class NewUserService {
    private final UserDao userDao = new UserDao();

    public void createNewUser(User user) {
        userDao.save(user);
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
