package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/home-page")
public class HomePageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        List<WeatherDto> weatherForecasts = locationService.getUserForecasts(user);
        context.setVariable("forecasts", weatherForecasts);
        engine.process("homePage", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String longitude = req.getParameter("longitude");
        String latitude = req.getParameter("latitude");

        locationService.deleteLocation(new BigDecimal(longitude), new BigDecimal(latitude));
        resp.sendRedirect(req.getContextPath() + "/home-page");
    }

    private User getUserFromSession(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(n -> n.getName().equals("session_id")).findFirst().orElse(null);
        assert cookie != null;
        Session session = authService.getSession(cookie.getValue());
        return session.getUser();
    }
}
