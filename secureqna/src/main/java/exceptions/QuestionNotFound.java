package exceptions;

public class QuestionNotFound extends Exception{

    public QuestionNotFound(){
        System.out.println("La pregunta solicitada no existe o no ha sido encontrada");
    }

}
