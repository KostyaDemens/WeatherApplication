package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.dto.LocationDto;
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
//        weatherService.getLocationsByName(locationName);
        List<LocationDto> locations = weatherService.getLocationsByName(locationName);
        int i = 2;
        req.getRequestDispatcher("/templates/homePage.html").forward(req, resp);
    }
}
