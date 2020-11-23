package iot.server.advice.exception;

public class SterilizerNotRunException extends RuntimeException {
    public SterilizerNotRunException() {
        super();
    }

    public SterilizerNotRunException(String message) {
        super(message);
    }

    public SterilizerNotRunException(String message, Throwable cause) {
        super(message, cause);
    }
}
