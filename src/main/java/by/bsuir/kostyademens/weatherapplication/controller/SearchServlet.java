package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.WeatherDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import by.bsuir.kostyademens.weatherapplication.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String locationName = req.getParameter("location_name");
        User user = (User)req.getAttribute("user");

        List<WeatherDto> forecasts = locationService.getForecasts(locationName);

        for (WeatherDto weatherDto : forecasts) {
            boolean hasLocation = userService.isUserHasLocation(user, weatherDto);
            weatherDto.setHasLocation(hasLocation);
            context.setVariable("hasLocation", hasLocation);
        }
                context.setVariable("forecasts", forecasts);

        engine.process("homePage", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User)req.getAttribute("user");

        String name = req.getParameter("name");
        BigDecimal latitude = new BigDecimal(req.getParameter("latitude"));
        BigDecimal longitude = new BigDecimal(req.getParameter("longitude"));

        Location location = new Location(name, user, latitude, longitude);


        locationService.saveLocation(location);


        resp.sendRedirect("/home-page");
    }
}
