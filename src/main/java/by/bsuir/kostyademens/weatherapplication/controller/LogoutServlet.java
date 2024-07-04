package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.model.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(n -> n.getName().equals("session_id")).findFirst().orElse(null);
        assert cookie != null;
        Session session = authService.getSession(cookie.getValue());
        session.setExpiresAt(LocalDateTime.now());
        resp.sendRedirect(req.getContextPath() + "/authorization");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
