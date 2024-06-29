package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/home-page")
public class HomePageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String locationName = req.getParameter("location_name");

        User user = getUserFromSession(req);
        Location modelLocation;
        if (locationName != null) {
        List<LocationDto> locations = weatherService.getLocationsByName(locationName);
            for (LocationDto location : locations) {
                modelLocation = locationMapper.convertToModel(location);
                boolean hasLocation = userService.isUserHasLocation(user, modelLocation);
                location.setHasLocation(hasLocation);
                location.setWeatherDto(weatherService.getWeatherForLocation(location));
            }

        context.setVariable("locations", locations);


        engine.process("homePage", context, resp.getWriter());
        return;
        }
        req.getRequestDispatcher("/templates/homePage.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);

        String name = req.getParameter("name");
        BigDecimal latitude = new BigDecimal(req.getParameter("latitude"));
        BigDecimal longitude = new BigDecimal(req.getParameter("longitude"));



        Location location = new Location(name, user, latitude, longitude);
        weatherService.saveLocation(location);

        resp.sendRedirect("/home-page?location_name=" + name);

    }

    private User getUserFromSession(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(n -> n.getName().equals("session_id")).findFirst().orElse(null);
        assert cookie != null;
        Session session = authService.getSession(cookie.getValue());
        return session.getUser();
    }
}
