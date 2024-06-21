package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
import by.bsuir.kostyademens.weatherapplication.model.Location;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home-page")
public class HomePageServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locationName = req.getParameter("location_name");

        if (locationName != null) {
        List<LocationDto> locations = weatherService.getLocationsByName(locationName);
        context.setVariable("locations", locations);
        engine.process("homePage", context, resp.getWriter());
        return;
        }
        req.getRequestDispatcher("/templates/homePage.html").forward(req, resp);
    }
}
