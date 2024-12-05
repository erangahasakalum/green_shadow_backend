package lk.ijse.gdse67.greenShadow.exeption;

public class UerNotFoundExseption extends RuntimeException {
    public UerNotFoundExseption(){
        super();
    }
    public UerNotFoundExseption(String message){
        super(message);
    }
    public UerNotFoundExseption(String message,Throwable cause){
        super(message,cause);
    }
}
