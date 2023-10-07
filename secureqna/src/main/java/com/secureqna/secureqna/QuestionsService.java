package com.secureqna.secureqna;

import objects.Questions;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class QuestionsService {

    private Map<Integer, Questions> allQuestions = new HashMap<>();

    public Collection<Questions> getRandomQuestionsList(){
        int n = 10; //por ejemplo, mostramos 10 preguntas
        Collection<Questions> questions = allQuestions.values();
        int maxQuestions=allQuestions.size();
        Random rand = new Random();
        int numeroAleatorio;
        for (int i=0;i<n;i++){  //siendo n el numero de preguntas a mostrarr en la pagina
            numeroAleatorio = rand.nextInt(maxQuestions) + 1;
            if (questions.contains(allQuestions.get(numeroAleatorio))){
                questions.remove(allQuestions.get(numeroAleatorio));
            }
        }
        return questions;
    }






}
