package gp.interviewtechnicalchallenge.exception;

public class NotEnoughInfoToDecodeException extends Exception {
    public NotEnoughInfoToDecodeException() {
        super("There is not enough information to decode the message");
    }
}