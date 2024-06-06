package by.bsuir.kostyademens.weatherapplication.controller.auth;

import by.bsuir.kostyademens.weatherapplication.controller.BaseServlet;
import by.bsuir.kostyademens.weatherapplication.dto.UserReqDto;
import by.bsuir.kostyademens.weatherapplication.exception.AuthorizationException;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        UserReqDto userReqDto = new UserReqDto(email, password);

        try {
            if (email != null && password != null) {
                Session session = authService.login(userReqDto);
                resp.addCookie(authService.getNewCookie(session));
                resp.sendRedirect(req.getContextPath() + "/home-page");
            }
        } catch (AuthorizationException e) {
            context.setVariable("authError", e.getMessage());
        }
        engine.process("authorization", context, resp.getWriter());

//        UserReqDto userReqDto = userService.findByLogin(email);
//
//        if (email != null && password != null) {
//
//        if (userReqDto != null && BCrypt.checkpw(password, userReqDto.getPassword())) {
//            resp.sendRedirect(req.getContextPath() + "/home-page");
//            return;
//        } else {
//            context.setVariable("authError", "Invalid username or password");
//        }
//
//        } else {
//            Session session = authorizationService.login(ModelMapperUtil.getModelMapper().map(userReqDto, User.class));
//            resp.addCookie(authorizationService.getNewCookie(session));
//            resp.sendRedirect(req.getContextPath() + "/home-page");
//        }
//        engine.process("authorization", context, resp.getWriter());
    }
}
