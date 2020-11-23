package iot.server.advice.exception;

public class DuplicateSterilizerSerialNumberException extends RuntimeException {
    public DuplicateSterilizerSerialNumberException() {
        super();
    }

    public DuplicateSterilizerSerialNumberException(String message) {
        super(message);
    }

    public DuplicateSterilizerSerialNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
