package com.secureqna.secureqna.controllers;


import com.secureqna.secureqna.exceptions.userExceptions.UserNotFound;
import com.secureqna.secureqna.objects.UserSqna;
import com.secureqna.secureqna.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/secureQnA/user")
public class UserController {

    @Autowired
    private UserService service = new UserService();

    @GetMapping("/updateScore")
    public String updateScore(@RequestParam long score, HttpServletRequest request) {
        Principal possible = request.getUserPrincipal();
        if (possible != null) {
            try {
                UserSqna usera = this.service.getUser(possible.getName());
                usera.addScore(score);
                service.modUser(usera.getUsername(), usera);
                return "redirect:/secureQnA/ranking";
            } catch (UserNotFound userNotFound) {
                System.out.println(userNotFound.getCause() + userNotFound.getMessage());
                return "error";
            }
        }else{
            return "error";
        }
    }





}
