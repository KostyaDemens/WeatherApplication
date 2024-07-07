package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.CoordinatesDto;
import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/search")
public class SearchPageServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getAttribute("user");
        String locationName = req.getParameter("locationName");
        List<LocationDto> locations = locationService.findLocationsByName(locationName);
        for (LocationDto location : locations) {
            if (userService.isUserHasLocation(user, location)) {
                boolean isHasLocation = true;
                location.setHasLocation(isHasLocation);
                context.setVariable("hasLocation", isHasLocation);
            }
        }

        context.setVariable("forecasts", locations);
        engine.process("homePage", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getAttribute("user");
        CoordinatesDto coordinates = CoordinatesDto.builder()
                .lat(new BigDecimal(req.getParameter("latitude")))
                .lon(new BigDecimal(req.getParameter("longitude")))
                .build();
        String name = req.getParameter("name");
        String locationName = req.getParameter("locationName");

        locationService.save(name, coordinates, user);

        String encodedLocationName = URLEncoder.encode(locationName, StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/search?locationName=" + encodedLocationName);
    }
}
