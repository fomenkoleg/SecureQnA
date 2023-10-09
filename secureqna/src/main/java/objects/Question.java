package objects;

import lombok.Data;

import java.util.List;

@Data
public class Question {

    private static int contador=0;

    private int id;

    private String question;

    private List<String> possibleAnswer;

    private int correctAnswer;

    public boolean checkAnswer(String possible){
        return possible.equals(possibleAnswer.get(correctAnswer));
    }

    public Question(){
        id=contador++;
        contador+=1;
    }
    public Question(String question, List<String> possibleAnswer){

    }


}
