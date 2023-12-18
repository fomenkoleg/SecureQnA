package com.secureqna.secureqna.controllers;

import com.google.gson.Gson;
import com.secureqna.secureqna.objects.Question;
import com.secureqna.secureqna.objects.QuestionJSON;
import com.secureqna.secureqna.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/secureQnA/questions")
public class QuestionsController {

    //aun no sirven pero los explico con comentarios.

    @Autowired
    private QuestionsService allQuestions= new QuestionsService();

    @GetMapping("/easy")
    public String getQuestionsEasy(Model model, HttpServletRequest request){  //mostrara la pagina con las preguntas para responder
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        } else {
            model.addAttribute("logged", true);
        }
        Collection<Question> questions=allQuestions.getRandomQuestionsList(10, 1);
        List<QuestionJSON> jsonQuestions = new ArrayList<>();
        int counter = 0;

        for (Question q : questions){
            counter++;
            if(q.getDifficulty() == 1){
                QuestionJSON newQ = new QuestionJSON(q);
                jsonQuestions.add(newQ);
            }

        }

        Gson gson = new Gson();
        String jsonPreguntas = gson.toJson(jsonQuestions);
        System.out.println("json = " + counter);
        model.addAttribute("jsonPreguntas", jsonPreguntas);
        return "preguntas";
    }


    @GetMapping("/medium")
    public String getQuestionsMid(Model model, HttpServletRequest request){  //mostrara la pagina con las preguntas para responder
        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        } else {
            model.addAttribute("logged", true);
        }
        Collection<Question> questions=allQuestions.getRandomQuestionsList(10,2);
        List<QuestionJSON> jsonQuestions = new ArrayList<>();
        int counter = 0;

        for (Question q : questions){
            counter++;
            if(q.getDifficulty() == 2){
                QuestionJSON newQ = new QuestionJSON(q);
                jsonQuestions.add(newQ);
            }

        }

        Gson gson = new Gson();
        String jsonPreguntas = gson.toJson(jsonQuestions);
        System.out.println("json = " + counter);
        model.addAttribute("jsonPreguntas", jsonPreguntas);
        return "preguntas";
    }

    @GetMapping("/hard")
    public String getQuestionsHard(Model model, HttpServletRequest request){  //mostrara la pagina con las preguntas para responder

        Principal possible= request.getUserPrincipal();
        if (possible == null){
            model.addAttribute("logged", false);
        } else {
            model.addAttribute("logged", true);
        }
        Collection<Question> questions=allQuestions.getRandomQuestionsList(10,3);
        List<QuestionJSON> jsonQuestions = new ArrayList<>();
        int counter = 0;

        for (Question q : questions){
            counter++;
            System.out.println("The difficulty is: " + q.getDifficulty());
            /*
            if(q.getDifficulty() == 3){
                QuestionJSON newQ = new QuestionJSON(q);
                jsonQuestions.add(newQ);
            }

             */
            QuestionJSON newQ = new QuestionJSON(q);
            jsonQuestions.add(newQ);
        }

        Gson gson = new Gson();
        String jsonPreguntas = gson.toJson(jsonQuestions);
        System.out.println("json = " + counter);
        model.addAttribute("jsonPreguntas", jsonPreguntas);
        return "preguntas";
    }

    @PostMapping("/ranking")
    public String ranking(Model model, HttpServletRequest request){
        return "ranking";
    }

}
