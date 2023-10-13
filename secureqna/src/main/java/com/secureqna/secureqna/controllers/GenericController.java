package com.secureqna.secureqna.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.secureqna.secureqna.objects.Question;
import com.secureqna.secureqna.objects.QuestionJSON;
import com.secureqna.secureqna.repositories.QuestionsRepository;
import com.secureqna.secureqna.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class GenericController {

    @Autowired
    private QuestionsService allQuestions= new QuestionsService();

    @Autowired
    private QuestionsRepository repository;


    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/questionsStart")
    public String questions(Model model){
        Collection<Question> questions=allQuestions.getRandomQuestionsList();
        List<QuestionJSON> jsonQuestions = new ArrayList<>();

        for (Question q : questions){
            QuestionJSON newQ = new QuestionJSON(q);
            jsonQuestions.add(newQ);
        }

        Gson gson = new Gson();
        String jsonPreguntas = gson.toJson(jsonQuestions);
        System.out.println("json = " + jsonPreguntas);
        model.addAttribute("jsonPreguntas", jsonPreguntas);
        return "preguntas";
    }

}
