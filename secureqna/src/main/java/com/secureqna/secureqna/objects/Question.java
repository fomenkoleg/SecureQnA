package com.secureqna.secureqna.objects;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Getter
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answers> possibleAnswer;

    private String correctAnswer;
    //la dificultad puede ser de 1-3
    private int difficulty;

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
