package by.bsuir.kostyademens.weatherapplication.listener;

import by.bsuir.kostyademens.weatherapplication.service.NewUserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        context.setAttribute("newUserService", new NewUserService());
    }
}
