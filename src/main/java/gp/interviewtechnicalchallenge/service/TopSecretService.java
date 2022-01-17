package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.exception.NonExistentSatelliteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TopSecretService {
    protected final List<String> satelliteNames;
    protected final Map<String, Message> messageMap = new HashMap<>();

    @Autowired
    public TopSecretService(List<String> satelliteNames) {
        this.satelliteNames = satelliteNames;
    }

    public void setMessage(Message message) throws NonExistentSatelliteException {
        if(!satelliteNames.contains(message.name))
            throw new NonExistentSatelliteException(message.name);
        messageMap.put(message.name, message);
    }

    public void setAllMessages(Message[] messages) throws NonExistentSatelliteException {
        for (Message message : messages)
            setMessage(message);
    }

    protected List<Message> getAllMessages() {
        return satelliteNames.stream().map(messageMap::get).collect(Collectors.toList());
    }
}
