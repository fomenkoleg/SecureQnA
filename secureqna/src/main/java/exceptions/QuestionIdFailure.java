package exceptions;

public class QuestionIdFailure extends Exception{

    public QuestionIdFailure(){
        System.out.println("El id se esta repitiendo");
    }
}
