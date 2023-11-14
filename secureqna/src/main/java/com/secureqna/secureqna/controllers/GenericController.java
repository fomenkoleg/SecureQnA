package com.secureqna.secureqna.controllers;


import com.secureqna.secureqna.exceptions.userExceptions.UserOnRegisterAlreadyIn;
import com.secureqna.secureqna.objects.UserSqna;
import com.secureqna.secureqna.services.QuestionsService;
import com.secureqna.secureqna.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.SchemaOutputResolver;
import java.security.Principal;




/*
    Generic Controller is used for login / register (also logout)
    In this controller, we can catch the main page.
     */


@Controller
@RequestMapping("/secureQnA")
public class GenericController {

    @Autowired
    private QuestionsService allQuestions= new QuestionsService();

    @Autowired
    private UserService service = new UserService();


    @GetMapping("/home")
    public String index(Model model, HttpServletRequest request){
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        }else {
            model.addAttribute("logged", true);
            model.addAttribute("name", possible.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request){
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        }else {
            model.addAttribute("logged", true);
        }
        return "login";
    }

    @GetMapping("/signUp")
    public String register(Model model, HttpServletRequest request){
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        }else {
            model.addAttribute("logged", true);
        }
        return "register";
    }

    @PostMapping("/signUp/verify")
    public String clientCreator(@ModelAttribute UserSqna newClient) {
        try {
            service.add(newClient);
            return "redirect:/secureQnA/login";
        }catch (UserOnRegisterAlreadyIn userOnRegisterAlreadyIn){
            return "error";
        }
    }

    @GetMapping("/comingSoon")
    public String comingSoon(Model model, HttpServletRequest request){
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        }else {
            model.addAttribute("logged", true);
        }
        return "comingSoon";
    }
}
