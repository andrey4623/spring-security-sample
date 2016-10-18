package com.andrey4623.web.controller;

import javax.servlet.http.HttpServletRequest;
import com.andrey4623.users.service.ExtendedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static final String INVALID_USERNAME_AND_PASSWORD = "Неправильное имя пользователя и/или пароль.";
    private static final String LOGGED_OUT = "Вы вышли из системы.";
    private static final String APPLICATION_TITLE = "Демонстрация Spring Security + Hibernate";

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", APPLICATION_TITLE);

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken)) {
             ExtendedUser user = (ExtendedUser) auth.getPrincipal();
            // User is authenticated.
            final String username = user.getName();
            model.addObject("name", username);
        }

        model.setViewName("hello");
        return model;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", APPLICATION_TITLE);
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", LOGGED_OUT);
        }
        model.setViewName("login");

        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = INVALID_USERNAME_AND_PASSWORD;
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = INVALID_USERNAME_AND_PASSWORD;
        }

        return error;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;
    }
}
