package com.secureqna.secureqna.objects;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answers> possibleAnswer;

    private String correctAnswer;

    public boolean checkAnswer(Answers possible){
        return possible.getContent().equals(correctAnswer);
    }

    public Question(String question, List<Answers> possibleAnswer){
        this.question = question;
        this.possibleAnswer = possibleAnswer;
    }

    public Question(String question){
        this.question = question;
    }


}
