package by.bsuir.kostyademens.weatherapplication.service.controller;

import by.bsuir.kostyademens.weatherapplication.controller.BaseServlet;
import by.bsuir.kostyademens.weatherapplication.controller.auth.RegistrationServlet;
import by.bsuir.kostyademens.weatherapplication.exception.PasswordMismatchException;
import by.bsuir.kostyademens.weatherapplication.service.RegistrationService;
import by.bsuir.kostyademens.weatherapplication.validator.PasswordValidator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServletTest {

    @InjectMocks
    private RegistrationServlet registrationServlet;

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private ITemplateEngine templateEngine;


    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        registrationServlet = new RegistrationServlet();

        Field templateField = registrationServlet.getClass().getSuperclass().getDeclaredField("templateEngine");
        templateField.setAccessible(true);
        templateField.set(registrationServlet, templateField);
    }



    @Test
    void doPost_PasswordMismatchShouldThrowAnException() throws IOException {
        when(req.getParameter("email")).thenReturn("test@gmail.com");
        when(req.getParameter("password")).thenReturn("password123");
        when(req.getParameter("confirmedPassword")).thenReturn("password111");

        try (MockedStatic<PasswordValidator> mockedStatic = Mockito.mockStatic(PasswordValidator.class)) {
            mockedStatic.when(() -> PasswordValidator.validatePasswordMatch("password123", "password111"))
                    .thenThrow(PasswordMismatchException.class);
        }

        registrationServlet.doPost(req, resp);

//        verify(context).setVariable(eq("passwordError"), eq("Please, ensure passwords are the same"));

//        verify(templateEngine).process(eq("registration"), eq(context), any());
W

    }
}
