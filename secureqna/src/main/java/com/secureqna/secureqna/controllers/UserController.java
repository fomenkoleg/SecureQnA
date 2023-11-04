package com.secureqna.secureqna.controllers;


import com.secureqna.secureqna.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secureQnA/user")
public class UserController {

    @Autowired
    private UserService service = new UserService();





}
