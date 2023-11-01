package com.secureqna.secureqna.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping("/loginPage")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/registerPage")
    public String register(Model model){
        return "register";
    }

}
