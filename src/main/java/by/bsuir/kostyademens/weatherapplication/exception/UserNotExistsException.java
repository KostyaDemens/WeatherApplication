package by.bsuir.kostyademens.weatherapplication.exception;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String message) {
        super(message);
    }
}
