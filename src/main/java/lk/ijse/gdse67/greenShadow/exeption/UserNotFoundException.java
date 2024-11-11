package lk.ijse.gdse67.greenShadow.exeption;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {

    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
