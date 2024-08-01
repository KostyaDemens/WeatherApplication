package by.bsuir.kostyademens.weatherapplication.filter;

import by.bsuir.kostyademens.weatherapplication.dao.SessionDao;
import by.bsuir.kostyademens.weatherapplication.dao.UserDao;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import by.bsuir.kostyademens.weatherapplication.service.AuthorizationService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class UserSessionFilter implements Filter {

  private AuthorizationService authService;
  private final SessionDao sessionDao = new SessionDao();
  private final UserDao userDao = new UserDao();

  @Override
  public void init(FilterConfig filterConfig) {
    authService = new AuthorizationService(sessionDao, userDao);
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
    Cookie[] cookies = httpRequest.getCookies();

    if (httpRequest.getRequestURI().equals("/authorization")
        || httpRequest.getRequestURI().equals("/registration")) {
      filterChain.doFilter(servletRequest, servletResponse);
      return;
    }

    if (cookies != null) {

      Cookie cookie =
          Arrays.stream(cookies)
              .filter(n -> n.getName().equals("session_id"))
              .findFirst()
              .orElse(null);
      if (cookie != null) {
        Session session = authService.getSession(cookie.getValue());
        if (session != null) {
          if (session.getUser() != null) {
            if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
              sessionDao.delete(session);
              httpRequest
                  .getRequestDispatcher("/templates/sessionExpired.html")
                  .forward(httpRequest, httpResponse);
            } else {
              servletRequest.setAttribute("user", session.getUser());
              filterChain.doFilter(servletRequest, servletResponse);
              return;
            }
          }
        }
      }
    }

    httpResponse.sendRedirect(httpRequest.getContextPath() + "/authorization");
  }
}
