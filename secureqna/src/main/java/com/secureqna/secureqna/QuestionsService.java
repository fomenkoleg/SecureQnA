package com.secureqna.secureqna;

import exceptions.QuestionIdFailure;
import exceptions.QuestionNotFound;
import objects.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class QuestionsService {

    private Map<Integer, Question> allQuestions = new HashMap<>();

    public Collection<Question> getRandomQuestionsList(){
        int n = 10; //por ejemplo, mostramos 10 preguntas
        Collection<Question> questions = allQuestions.values();
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

    public Question getQuestion(int id)throws QuestionNotFound{
        Question question = allQuestions.get(id);
        if (question == null){
            throw new QuestionNotFound();
        }else {
            return question;
        }
    }

    public Question rmQuestion(int id)throws QuestionNotFound{
        Question question = allQuestions.get(id);
        if (question == null){
            throw new QuestionNotFound();
        }else {
            return allQuestions.remove(question.getId());
        }
    }

    public void add(Question question)throws QuestionIdFailure {
        if (this.allQuestions.get(question.getId()) == null){
            allQuestions.put(question.getId(),question);
        }else {
            throw new QuestionIdFailure();
        }
    }







}
