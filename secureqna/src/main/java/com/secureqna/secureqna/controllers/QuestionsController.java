package com.secureqna.secureqna.controllers;

import com.google.gson.Gson;
import com.secureqna.secureqna.objects.Question;
import com.secureqna.secureqna.objects.QuestionJSON;
import com.secureqna.secureqna.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class QuestionsController {

    //aun no sirven pero los explico con comentarios.

    @Autowired
    private QuestionsService allQuestions= new QuestionsService();

    @GetMapping("/questions")
    public String getQuestions(Model model){  //mostrara la pagina con las preguntas para responder
        Collection<Question> questions=allQuestions.getRandomQuestionsList(10);
        List<QuestionJSON> jsonQuestions = new ArrayList<>();
        int counter = 0;
        for (Question q : questions){
            counter++;
            QuestionJSON newQ = new QuestionJSON(q);
            jsonQuestions.add(newQ);
        }
        Gson gson = new Gson();
        String jsonPreguntas = gson.toJson(jsonQuestions);
        System.out.println("json = " + counter);
        model.addAttribute("jsonPreguntas", jsonPreguntas);
        return "preguntas";
    }

    @GetMapping("/questions/ressult")
    public String getRessult(@ModelAttribute List<Integer> solutions){  //es la que recibe las respuestas y procesa en el service el resultado

        return "finalScore";
    }


}
