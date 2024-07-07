package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.CoordinatesDto;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/home-page")
public class HomePageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getAttribute("user");
        List<LocationDto> userLocations = userService.getUserLocations(user);
        if (!userLocations.isEmpty()) {
            context.setVariable("forecasts", userLocations);
        }
        engine.process("homePage", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CoordinatesDto coordinatesDto = CoordinatesDto.builder()
                .lon(new BigDecimal(req.getParameter("longitude")))
                .lat(new BigDecimal(req.getParameter("latitude")))
                .build();

        User user = (User)req.getAttribute("user");

        locationService.delete(coordinatesDto, user);
        engine.process("homePage", context, resp.getWriter());
    }
}
