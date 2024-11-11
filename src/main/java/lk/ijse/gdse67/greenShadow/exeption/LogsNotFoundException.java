package lk.ijse.gdse67.greenShadow.exeption;

public class LogsNotFoundException extends RuntimeException{
    public LogsNotFoundException() {

    }

    public LogsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogsNotFoundException(Throwable cause) {
        super(cause);
    }
}
