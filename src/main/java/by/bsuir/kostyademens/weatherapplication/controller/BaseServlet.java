package by.bsuir.kostyademens.weatherapplication.controller;

import by.bsuir.kostyademens.weatherapplication.service.NewUserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

public class BaseServlet extends HttpServlet {

    protected NewUserService userService = new NewUserService();
    protected TemplateEngine engine;

    protected WebContext context;
    protected IWebExchange webExchange;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        engine = (TemplateEngine) getServletContext().getAttribute("templateEngine");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
