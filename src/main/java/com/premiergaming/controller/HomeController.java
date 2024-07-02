package com.premiergaming.controller;
import com.premiergaming.service.interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    IUsersService usersService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        return new ModelAndView("accessDenied");
    }


}

