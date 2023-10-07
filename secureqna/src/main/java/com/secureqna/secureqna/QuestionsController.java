package com.secureqna.secureqna;

import objects.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class QuestionsController {

    //aun no sirven pero los explico con comentarios.

    @Autowired
    private QuestionsService allQuestions= new QuestionsService();

    @GetMapping("/questions")
    public String getQuestions(Model model){  //mostrara la pagina con las preguntas para responder
        Collection<Questions> allQuestionsRndom=allQuestions.getRandomQuestionsList();
        model.addAttribute("allQuestions",allQuestions);
        return "questions";
    }

    @GetMapping("/questions/ressult")
    public String getRessult(Model model){  //es la que recibe las respuestas y procesa en el service el resultado

        return "finalScore";
    }


}
