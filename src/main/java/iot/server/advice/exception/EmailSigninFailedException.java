package iot.server.advice.exception;

public class EmailSigninFailedException extends RuntimeException {

    public EmailSigninFailedException() {
        super();
    }

    public EmailSigninFailedException(String message) {
        super(message);
    }

    public EmailSigninFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
