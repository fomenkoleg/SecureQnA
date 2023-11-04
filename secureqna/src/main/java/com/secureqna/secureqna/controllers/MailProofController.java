package com.secureqna.secureqna.controllers;


import com.secureqna.secureqna.objects.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secureQnA/mail")
public class MailProofController {

    @Autowired
    private JavaMailSender javaMailSender;


    @PostMapping("/send")
    public String handleContactForm(@ModelAttribute Mail message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("secureqa.feedback@gmail.com");
        mailMessage.setSubject(message.getTitle());
        mailMessage.setText(message.getUsername()+" pregunta:\n"+message.getContent()+"\nSu correo para responderle es: "+message.getEmail());
        javaMailSender.send(mailMessage);
        return "index";
    }
}
