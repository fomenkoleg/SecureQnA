package com.secureqna.secureqna;

import exceptions.QuestionIdFailure;
import exceptions.QuestionNotFound;
import objects.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionsRestController {

    @Autowired
    QuestionsService service = new QuestionsService();

    @DeleteMapping("/remove/{username}")
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
