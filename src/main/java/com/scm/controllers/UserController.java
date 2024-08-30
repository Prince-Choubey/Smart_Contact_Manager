package com.scm.controllers;


import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {


    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

   
    // User dashboard page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    // User Profile page

    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {
    
        return "user/profile";
    }
    

    // User add Contacts page


    // User view contacts


    // User edit contact


    // user delete contact


    // User search contact


}
