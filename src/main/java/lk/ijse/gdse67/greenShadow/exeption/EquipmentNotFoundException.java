package lk.ijse.gdse67.greenShadow.exeption;

public class EquipmentNotFoundException extends RuntimeException{
    public EquipmentNotFoundException() {

    }

    public EquipmentNotFoundException(String message) {
        super(message);
    }

    public EquipmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}