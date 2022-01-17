package gp.interviewtechnicalchallenge.exception;

public class NotEnoughInfoToTriangulateException extends Exception {
    public NotEnoughInfoToTriangulateException() {
        super("There is not enough information to triangulate the position");
    }
}