package by.bsuir.kostyademens.weatherapplication.validator;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordValidator {
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
