package com.aynroot.cinemamanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "admin";
    }

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required=false) String message) {
        model.addAttribute("message", message);
        return "login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Не удалось войти";
        return "redirect:/login?message="+message;
    }

    @RequestMapping(value = "/logout/success")
    public String logoutSuccess() {
        return "redirect:/login";
    }

}