package com.secureqna.secureqna.services;

import com.google.gson.Gson;
import com.secureqna.secureqna.repositories.AnswerRepository;
import com.secureqna.secureqna.repositories.QuestionsRepository;
import com.secureqna.secureqna.exceptions.QuestionIdFailure;
import com.secureqna.secureqna.exceptions.QuestionNotFound;
import com.secureqna.secureqna.objects.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionsService {




    @Autowired
    private QuestionsRepository repository;

    @Autowired
    private AnswerRepository answerRepository;


    public List<Question> getRandomQuestionsList(int cantidad) {
        List<Question> todasLasPreguntas = repository.findAll();

        // Verificar que la cantidad solicitada no sea mayor que el total de preguntas disponibles
        if (cantidad > todasLasPreguntas.size()) {
            throw new IllegalArgumentException("La cantidad solicitada es mayor que el número total de preguntas");
        }

        // Mezclar las preguntas
        Collections.shuffle(todasLasPreguntas);

        // Obtener las primeras 'cantidad' preguntas
        return todasLasPreguntas.subList(0, cantidad);
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

    public void addRaw(List<Question> questions){
        for (Question question : questions) {
            try {
                add(question);
            } catch (QuestionIdFailure questionIdFailure) {
                System.out.println("TOTNTO");
            }
        }
    }

    public List<Question> getQuestionList(){
        List<Question> questions = this.repository.findAll();
        return questions;
    }











}
