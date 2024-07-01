package com.premiergaming.controller;

import com.premiergaming.model.Error;
import com.premiergaming.model.Users;
import com.premiergaming.service.interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {
    @Autowired
    IUsersService usersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/createUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView createUser() {
        ModelAndView mav = new ModelAndView("admin/createUser");
        Users user = new Users();
        mav.addObject(user);

        return mav;
    }

    @PostMapping("/createUser/success")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView save(@ModelAttribute Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users existingUser = usersService.getUserByEmail(user.getEmail());
        if (existingUser == null) {
            Users result = usersService.create(user);
            if (result.getUserId() >0){
                ModelAndView mav = new ModelAndView("admin/displayUser");
                mav.addObject("Users", user);
                mav.addObject("isCreateUser", true);
                return mav;
            }
        }else {
            Error error = new Error();
            error.setErrorMessage("This user is existed");
            ModelAndView mav = new ModelAndView("admin/createUser");
            mav.addObject(user);
            mav.addObject(error);
            return mav;
        }

        return null;
    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView viewUser(@PathVariable("id") Integer id) {
        ModelAndView modelAndView =  new ModelAndView("/admin/displayUser");
        Users user = usersService.getByUserId(id);
        modelAndView.addObject("users", user);
        modelAndView.addObject("isCreateUser", false);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView deleteUser(@PathVariable("id") Integer id) {
        usersService.deleteUserByUserId(id);
        ModelAndView modelAndView =  new ModelAndView("redirect:/admin/dashboard");
        return modelAndView;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView adminDashboard() {
        ModelAndView mav = new ModelAndView("admin/dashboard");
        List<Users> users = usersService.getAll();
        mav.addObject("users", users);

        return mav;
    }


}
