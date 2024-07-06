package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/home-page")
public class HomePageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User)req.getAttribute("user");
        List<WeatherDto> weatherForecasts = locationService.getUserForecasts(user);
        if (!weatherForecasts.isEmpty()) {
        context.setVariable("forecasts", weatherForecasts);
        }
        engine.process("homePage", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String longitude = req.getParameter("longitude");
        String latitude = req.getParameter("latitude");
        User user = (User)req.getAttribute("user");


        locationService.deleteLocation(new BigDecimal(longitude), new BigDecimal(latitude), user);
        resp.sendRedirect(req.getContextPath() + "/home-page");
    }
}
