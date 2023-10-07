package objects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Questions {

    private static int contador=0;

    private int id;

    private String question;

    private List<String> possibleAnswer;

    private int correctAnswer;

    public boolean checkAnswer(String possible){
        return possible.equals(possibleAnswer.get(correctAnswer));
    }

    public Questions(){
        id=contador++;
        contador+=1;
    }
    public Questions(String question, List<String> possibleAnswer){

    }


}
