package com.premiergaming.controller;

import com.premiergaming.model.Users;
import com.premiergaming.service.interfaces.IUsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    IUsersService usersService;

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ModelAndView getUser() {

        Users users = usersService.getUserByEmail(loggedInUserDetails().getUsername());
        if (users != null) {
            ModelAndView mav = new ModelAndView("user/home");
            mav.addObject(users);
            boolean isAdmin = false;
            if (users.getRole().equals("ADMIN")){
                isAdmin = true;
            }
            mav.addObject("isAdmin",isAdmin);
            return mav;
        }
        return new ModelAndView("/login");
    }
    public UserDetails loggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }


}