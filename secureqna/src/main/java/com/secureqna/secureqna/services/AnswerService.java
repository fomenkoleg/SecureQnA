package com.secureqna.secureqna.services;

import com.secureqna.secureqna.exceptions.AnswerIdFailure;
import com.secureqna.secureqna.exceptions.AnswerNotFound;
import com.secureqna.secureqna.repositories.AnswerRepository;
import com.secureqna.secureqna.objects.Answers;
import com.secureqna.secureqna.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    private QuestionsRepository questionsRepository;



    public void add(Answers possible, long idQuestion)throws AnswerIdFailure {
        Answers answers = repository.findById(possible.getId()).orElse(null);
        if (answers != null){
            throw new AnswerIdFailure();
        }else {
            this.repository.save(possible);
        }
    }

    public Answers rmAnswer(long id)throws AnswerNotFound {
        Answers answers = repository.findById(id).orElse(null);
        if (answers == null){
            throw new AnswerNotFound();
        }else {
            this.repository.delete(answers);
            return answers;
        }
    }




}
