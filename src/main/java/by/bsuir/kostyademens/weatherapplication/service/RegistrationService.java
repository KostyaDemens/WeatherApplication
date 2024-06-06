package by.bsuir.kostyademens.weatherapplication.service;

import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.exception.EmailInvalidException;
import by.bsuir.kostyademens.weatherapplication.exception.UserAlreadyExistsException;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.validator.EmailValidator;
import org.mindrot.jbcrypt.BCrypt;


import java.util.Optional;

public class RegistrationService {
    private final UserDao userDao = new UserDao();

    public void register(User user) {
        Optional<User> potentialUser = userDao.findByLogin(user.getEmail());
        if (potentialUser.isPresent()) {
            throw new UserAlreadyExistsException("User with this username already exists");
        } else {
            if (EmailValidator.isValidEmail(user.getEmail())) {
                BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                createNewUser(user);
            } else {
                throw new EmailInvalidException("Please, enter a valid email address");
            }
        }
    }

    public void createNewUser(User user) {
        userDao.save(user);
    }

}
