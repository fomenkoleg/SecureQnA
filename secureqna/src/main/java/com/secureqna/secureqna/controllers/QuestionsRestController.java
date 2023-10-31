package com.secureqna.secureqna.controllers;

import com.secureqna.secureqna.exceptions.QuestionIdFailure;
import com.secureqna.secureqna.exceptions.QuestionNotFound;
import com.secureqna.secureqna.objects.Question;
import com.secureqna.secureqna.services.AnswerService;
import com.secureqna.secureqna.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionsRestController {

    @Autowired
    QuestionsService service = new QuestionsService();

    @Autowired
    AnswerService answerService = new AnswerService();

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Question> removeClient(@PathVariable int id) {
        try {
            Question question = service.rmQuestion(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }catch (QuestionNotFound questionNotFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Question> newClient(@RequestBody Question question){
        try {
            service.add(question);
            return new ResponseEntity<>(question,HttpStatus.CREATED);
        }catch (QuestionIdFailure questionIdFailure){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/supernew")
    public ResponseEntity<List<Question>> newClientRaw(@RequestBody List<Question> questions){
            service.addRaw(questions);
            return new ResponseEntity<>(questions,HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Question>> getAllQuestions(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/random")
    public ResponseEntity<Collection<Question>> getAllQuestionsRandom(){
        return new ResponseEntity<>(this.service.getRandomQuestionsList(10),HttpStatus.OK);
    }

    @GetMapping("/number")
    public ResponseEntity<Long> getNumberOfQuestions(){
        return new ResponseEntity<>(this.service.getNumber(), HttpStatus.OK);
    }



    /*
    @GetMapping("/all")
    public ResponseEntity<Collection<Client>> getAllClients(){
        try {
            Collection<Client>clients=this.service.GetAllClientsWithoutPass();
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }catch (NoClients noClients){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{username}")
    public ResponseEntity<Client>getClient(@PathVariable String username){
        try{
            Client client=service.getClient(username);
            return new ResponseEntity<>(Client.returnWithoutPass(client),HttpStatus.OK);
        }catch (UserNotFound userNotFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/mod/{username}")
    public ResponseEntity<Client>uploadClientInfo(@PathVariable String username, @RequestBody Client actClient){
        try{
            service.modClient(username,actClient);
            return new ResponseEntity<>(Client.returnWithoutPass(actClient),HttpStatus.OK);
        }catch (UserNotFound userNotFound){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



     */

}
