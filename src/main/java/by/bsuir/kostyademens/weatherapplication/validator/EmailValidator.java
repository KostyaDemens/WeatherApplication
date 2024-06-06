package by.bsuir.kostyademens.weatherapplication.validator;

import javax.mail.internet.InternetAddress;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        try {
            new InternetAddress(email).validate();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
