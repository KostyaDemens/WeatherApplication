package by.bsuir.kostyademens.weatherapplication.controller.auth;


import by.bsuir.kostyademens.weatherapplication.controller.BaseServlet;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

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

        if (email != null && password != null && confirmedPassword != null) {
        if (!userService.isValidEmail(email)) {
            context.setVariable("emailError", "Please, enter a valid email address");
        } else if (userService.isUserExists(email)) {
            context.setVariable("emailError", "User with this username already exists");
        }
        if (!password.equals(confirmedPassword)) {
            context.setVariable("passwordError", "Please, ensure passwords are the same");
        } else if (userService.isValidEmail(email) && !userService.isUserExists(email)) {

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User(email, hashedPassword);
            userService.createNewUser(user);
            resp.sendRedirect(req.getContextPath() + "/authorization");
            return;
        }
        } else {
            resp.sendRedirect(req.getContextPath() + "/registration");
        }

        engine.process("registration", context, resp.getWriter());

    }
}
