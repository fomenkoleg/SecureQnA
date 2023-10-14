package com.secureqna.secureqna.controllers;

import com.secureqna.secureqna.objects.Question;
import com.secureqna.secureqna.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;
import java.util.List;

@Controller
public class QuestionsController {

    //aun no sirven pero los explico con comentarios.

    @Autowired
    private QuestionsService allQuestions= new QuestionsService();

    @GetMapping("/questions")
    public String getQuestions(Model model){  //mostrara la pagina con las preguntas para responder
        Collection<Question> allQuestionsRndom=allQuestions.getRandomQuestionsList(10);
        model.addAttribute("allQuestions",allQuestions);
        return "questions";
    }

    @GetMapping("/questions/ressult")
    public String getRessult(@ModelAttribute List<Integer> solutions){  //es la que recibe las respuestas y procesa en el service el resultado

        return "finalScore";
    }


}
