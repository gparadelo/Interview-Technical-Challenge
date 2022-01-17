package gp.interviewtechnicalchallenge.exception;

public class NonExistentSatelliteException extends Exception {
    public final String satelliteName;

    public NonExistentSatelliteException(String satelliteName) {
        super("The satellite for this message does not exist");
        this.satelliteName = satelliteName;
    }
}
