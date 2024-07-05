package by.bsuir.kostyademens.weatherapplication.filter;

import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.service.AuthorizationService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class UserSessionFilter implements Filter {

    private AuthorizationService authService;
    @Override
    public void init(FilterConfig filterConfig) {
        authService = new AuthorizationService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Cookie[] cookies = httpRequest.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(n -> n.getName().equals("session_id")).findFirst().orElse(null);

        if (cookie != null) {
            Session session = authService.getSession(cookie.getValue());
            if (session != null) {
                servletRequest.setAttribute("user", session.getUser());
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
