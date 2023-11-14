package com.secureqna.secureqna.controllers;

import org.apache.http.impl.bootstrap.HttpServer;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class loginController {

    @GetMapping("/loginPage")
    public String login(Model model, HttpServletRequest request){
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        }else {
            model.addAttribute("logged", true);
        }
        return "login";
    }

    @GetMapping("/registerPage")
    public String register(Model model, HttpServletRequest request){
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        }else {
            model.addAttribute("logged", true);
        }
        return "register";
    }

}
