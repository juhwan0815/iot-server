package iot.server.advice.exception;

public class EmailExistedException extends RuntimeException{

    public EmailExistedException() {
        super();
    }

    public EmailExistedException(String message) {
        super(message);
    }

    public EmailExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
