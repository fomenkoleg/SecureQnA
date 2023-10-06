package com.secureqna.secureqna;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenericController {

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
}
