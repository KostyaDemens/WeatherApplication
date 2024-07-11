package by.bsuir.kostyademens.weatherapplication.controller.auth;

import by.bsuir.kostyademens.weatherapplication.controller.BaseServlet;
import by.bsuir.kostyademens.weatherapplication.dao.SessionDao;
import by.bsuir.kostyademens.weatherapplication.model.Session;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {

  private final SessionDao sessionDao = new SessionDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Cookie[] cookies = req.getCookies();
    Cookie cookie =
        Arrays.stream(cookies)
            .filter(n -> n.getName().equals("session_id"))
            .findFirst()
            .orElse(null);
    assert cookie != null;
    Session session = authService.getSession(cookie.getValue());
    sessionDao.delete(session);
    resp.sendRedirect(req.getContextPath() + "/authorization");
  }
}
