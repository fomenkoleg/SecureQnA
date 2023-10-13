package com.secureqna.secureqna.services;

import com.google.gson.Gson;
import com.secureqna.secureqna.repositories.AnswerRepository;
import com.secureqna.secureqna.repositories.QuestionsRepository;
import com.secureqna.secureqna.exceptions.QuestionIdFailure;
import com.secureqna.secureqna.exceptions.QuestionNotFound;
import com.secureqna.secureqna.objects.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class QuestionsService {




    @Autowired
    private QuestionsRepository repository;

    @Autowired
    private AnswerRepository answerRepository;


    public Collection<Question> getRandomQuestionsList(){
        int n = 10; //por ejemplo, mostramos 10 preguntas
        Collection<Question> questions = repository.findAll();
        int maxQuestions=questions.size();
        Random rand = new Random();
        long numeroAleatorio;
        for (int i=0;i<n;i++){  //siendo n el numero de preguntas a mostrarr en la pagina
            numeroAleatorio = rand.nextInt(maxQuestions) + 1;
            repository.findById(numeroAleatorio).ifPresent(questions::remove); //busca por id, si esta, lo borra de la lista
        }
        return questions;
    }

    public Question getQuestion(long id)throws QuestionNotFound{
        Question question = repository.findById(id).orElse(null);
        if (question == null){
            throw new QuestionNotFound();
        }else {
            return question;
        }
    }

    public Question rmQuestion(long id)throws QuestionNotFound{
        Question question = repository.findById(id).orElse(null);
        if (question == null){
            throw new QuestionNotFound();
        }else {
            repository.delete(question);
            return question;
        }
    }

    public void add(Question possible)throws QuestionIdFailure {
        Question question = repository.findById(possible.getId()).orElse(null);
        if (question == null){
            repository.save(possible);
        }else {
            throw new QuestionIdFailure();
        }
    }

    public Collection<Question> getAll(){
        return this.repository.findAll();
    }

    public Long getNumber(){
        return this.repository.count();
    }











}
