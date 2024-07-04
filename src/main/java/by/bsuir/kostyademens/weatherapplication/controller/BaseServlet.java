package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.mapper.LocationMapper;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.model.User;
import by.bsuir.kostyademens.weatherapplication.service.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Arrays;

public class BaseServlet extends HttpServlet {

    protected AuthorizationService authService = new AuthorizationService();
    protected RegistrationService registerService = new RegistrationService();
    protected OpenWeatherService weatherService = new OpenWeatherService();
    protected LocationMapper locationMapper = new LocationMapper();
    protected UserService userService = new UserService();
    protected LocationService locationService = new LocationService();
    protected TemplateEngine engine;

    protected WebContext context;
    protected IWebExchange webExchange;

    protected User getUserFromSession(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(n -> n.getName().equals("session_id")).findFirst().orElse(null);
        assert cookie != null;
        Session session = authService.getSession(cookie.getValue());
        return session.getUser();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        engine = (TemplateEngine) getServletContext().getAttribute("templateEngine");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        context = new WebContext(webExchange);
        try{
            super.service(req, resp);
        } catch (Exception e) {
            context.setVariable("error", e.getMessage());
            engine.process("error", context, resp.getWriter());
        }
    }


}
