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
    public String index(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/signUp")
    public String register(Model model){
        return "register";
    }

    @PostMapping("/signUp/verify")
    public String clientCreator(@ModelAttribute UserSqna newClient) {
        try {
            service.add(newClient);
            return "redirect:/gymnacio/login";
        }catch (UserOnRegisterAlreadyIn userOnRegisterAlreadyIn){
            return "redirect:/gymnacio/signUpError";
        }
    }


}
