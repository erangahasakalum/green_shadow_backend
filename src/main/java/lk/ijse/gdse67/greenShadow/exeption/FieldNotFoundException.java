package lk.ijse.gdse67.greenShadow.exeption;

public class FieldNotFoundException extends RuntimeException{
    public FieldNotFoundException() {

    }

    public FieldNotFoundException(String message) {
        super(message);
    }

    public FieldNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
