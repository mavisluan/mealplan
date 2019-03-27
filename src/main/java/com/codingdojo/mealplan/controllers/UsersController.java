package com.codingdojo.mealplan.controllers;

import com.codingdojo.mealplan.models.User;
import com.codingdojo.mealplan.services.UserService;
import com.codingdojo.mealplan.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class UsersController {
    private final UserService userService;
    private final UserValidator userValidator;

    public UsersController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "/users/registrationPage.jsp";
    }

    @RequestMapping("/login")
    public String login() {
        return "users/registrationPage.jsp";
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User formUser, BindingResult result, HttpSession session) {
        userValidator.validate(formUser, result);

        if (result.hasErrors()) {
            return "/users/registrationPage.jsp";
        } else {
            User u = userService.registerUser(formUser);
            session.setAttribute("userId", u.getId());
            return "redirect:/shows";
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(
            @Valid @ModelAttribute("user") User formUser, BindingResult result,
            @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {

        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            User u = userService.findByEmail(email);
            session.setAttribute("userId", u.getId());
            return "redirect:/shows";
        } else {
            model.addAttribute("error", "Invalid Credentials. Please try again.");
            return "/users/registrationPage.jsp";
        }
    }

    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);

        return "/users/homePage.jsp";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        session.invalidate();
        // redirect to login page
        return "redirect:/";
    }
}