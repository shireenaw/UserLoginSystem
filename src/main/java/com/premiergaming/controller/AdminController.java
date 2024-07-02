package com.premiergaming.controller;

import com.premiergaming.model.Error;
import com.premiergaming.model.dto.UsersDTO;
import com.premiergaming.service.interfaces.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
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
        UsersDTO user = new UsersDTO();
        mav.addObject(user);
        return mav;
    }

    @PostMapping("/createUser/success")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView save(@ModelAttribute UsersDTO user) {
        if (user.getFirstName().isBlank() || user.getLastName().isBlank() || user.getEmail().isBlank()
        || user.getPassword().isBlank()  || user.getRole().isBlank()) {
            Error error = new Error();
            error.setErrorMessage("Please fill in required fields");
            ModelAndView mav = new ModelAndView("admin/createUser");
            mav.addObject(user);
            mav.addObject(error);
            return mav;
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UsersDTO existingUser = usersService.getUserByEmail(user.getEmail());
            if (existingUser == null) {
                UsersDTO result = usersService.create(user);
                if (result.getUserId() >0){
                    ModelAndView mav = new ModelAndView("admin/displayUser");
                    mav.addObject(result);
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
        }
        return null;
    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView viewUser(@PathVariable("id") Integer id) {
        ModelAndView modelAndView =  new ModelAndView("/admin/displayUser");
        UsersDTO user = usersService.getByUserId(id);
        modelAndView.addObject(user);
        modelAndView.addObject("isCreateUser", false);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView deleteUser(@PathVariable("id") Integer id) {
        usersService.deleteUserByUserId(id);
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView adminDashboard() {
        ModelAndView mav = new ModelAndView("admin/dashboard");
        List<UsersDTO> users = usersService.getAll();
        mav.addObject("users", users);
        return mav;
    }


}
