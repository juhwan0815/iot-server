package iot.server.advice.exception;

public class SterilizerNotFoundException extends RuntimeException{
    public SterilizerNotFoundException() {
        super();
    }

    public SterilizerNotFoundException(String message) {
        super(message);
    }

    public SterilizerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
