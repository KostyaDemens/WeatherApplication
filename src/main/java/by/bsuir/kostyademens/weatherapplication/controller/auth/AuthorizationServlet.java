package by.bsuir.kostyademens.weatherapplication.controller.auth;

import by.bsuir.kostyademens.weatherapplication.controller.BaseServlet;
import by.bsuir.kostyademens.weatherapplication.dto.UserReqDto;
import by.bsuir.kostyademens.weatherapplication.mapper.ModelMapperUtil;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/templates/authorization.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        UserReqDto userReqDto = userService.findByLogin(email);

        if (email != null && password != null) {

        if (userReqDto != null && BCrypt.checkpw(password, userReqDto.getPassword())) {
            resp.sendRedirect(req.getContextPath() + "/home-page");
            return;
        } else {
            context.setVariable("authError", "Invalid username or password");
        }

        } else {
            Session session = authorizationService.login(ModelMapperUtil.getModelMapper().map(userReqDto, User.class));
            resp.addCookie(authorizationService.getNewCookie(session));
            resp.sendRedirect(req.getContextPath() + "/home-page");
        }
        engine.process("authorization", context, resp.getWriter());
    }
}
