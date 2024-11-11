package lk.ijse.gdse67.greenShadow.exeption;

public class CropNotFoundException extends RuntimeException{
    public CropNotFoundException() {
        super();
    }

    public CropNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CropNotFoundException(Throwable cause) {
        super(cause);
    }
}
