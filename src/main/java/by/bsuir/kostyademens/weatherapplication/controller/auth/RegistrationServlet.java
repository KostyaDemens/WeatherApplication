package by.bsuir.kostyademens.weatherapplication.controller.auth;


import by.bsuir.kostyademens.weatherapplication.controller.BaseServlet;
import by.bsuir.kostyademens.weatherapplication.exception.EmailInvalidException;
import by.bsuir.kostyademens.weatherapplication.exception.PasswordMismatchException;
import by.bsuir.kostyademens.weatherapplication.exception.UserAlreadyExistsException;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.validator.ParameterValidator;
import by.bsuir.kostyademens.weatherapplication.validator.PasswordValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmedPassword = req.getParameter("confirmedPassword");

        User user = new User(email, password);

        try {
            if (ParameterValidator.areNotNull(email, password, confirmedPassword)) {
                PasswordValidator.validatePasswordMatch(password, confirmedPassword);
                registerService.register(user);
                resp.sendRedirect(req.getContextPath() + "/authorization");
            }
        } catch (UserAlreadyExistsException | EmailInvalidException e) {
            context.setVariable("emailError", e.getMessage());
        } catch (PasswordMismatchException e) {
            context.setVariable("passwordError", e.getMessage());
        }

        engine.process("registration", context, resp.getWriter());

    }
}
