package gp.interviewtechnicalchallenge.service;

public class Message {
    public final String name;
    public final double distance;
    public final String[] message;

    public Message(String name, double distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
}
