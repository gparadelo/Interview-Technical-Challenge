package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.core.Location;
import gp.interviewtechnicalchallenge.core.LocationTriangulator;
import gp.interviewtechnicalchallenge.exception.NotEnoughInfoToTriangulateException;
import gp.interviewtechnicalchallenge.exception.TriangulationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocationTriangulatorService extends TopSecretService {

    private final LocationTriangulator locationTriangulator;

    @Autowired
    public LocationTriangulatorService(List<String> satelliteNames, LocationTriangulator locationTriangulator) {
        super(satelliteNames);
        this.locationTriangulator = locationTriangulator;
    }

    public Location getLocation() throws TriangulationException, NotEnoughInfoToTriangulateException {
        List<Message> messages = getAllMessages();
        if (messages.contains(null))
            throw new NotEnoughInfoToTriangulateException();
        double[] distances = messages.stream().map(m -> m.distance).mapToDouble(Double::doubleValue).toArray();
        return locationTriangulator.getLocation(distances);
    }
}
