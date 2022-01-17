package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.core.Location;
import gp.interviewtechnicalchallenge.core.LocationTriangulator;
import gp.interviewtechnicalchallenge.exception.NonExistentSatelliteException;
import gp.interviewtechnicalchallenge.exception.NotEnoughInfoToTriangulateException;
import gp.interviewtechnicalchallenge.exception.TriangulationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LocationTriangulatorServiceTests {

    Message[] messages;

    LocationTriangulatorService triangulatorService;

    @BeforeEach
    void setUp() {
        String[] names = {"kenobi", "skywalker", "sato"};
        Location[] locations = {new Location(-1, 0), new Location(-0, 2), new Location(4, 3)};

        LocationTriangulator triangulator = new LocationTriangulator(locations[0], locations[1], locations[2]);
        triangulatorService = new LocationTriangulatorService(List.of(names), triangulator);

        Message message1 = new Message(names[0], 1, new String[]{});
        Message message2 = new Message(names[1], 2, new String[]{});
        Message message3 = new Message(names[2], 5, new String[]{});

        messages = new Message[]{message1, message2, message3};
    }

    @Test
    void canGetLocation() throws TriangulationException, NonExistentSatelliteException, NotEnoughInfoToTriangulateException {
        triangulatorService.setAllMessages(messages);

        assertEquals(new Location(0, 0), triangulatorService.getLocation());
    }

    @Test
    void canGetSetMessagesSeparatelyAndGetLocation() throws TriangulationException, NonExistentSatelliteException, NotEnoughInfoToTriangulateException {
        triangulatorService.setMessage(messages[0]);
        triangulatorService.setMessage(messages[1]);
        triangulatorService.setMessage(messages[2]);

        assertEquals(new Location(0, 0), triangulatorService.getLocation());
    }

    @Test
    void exceptionWhenInsufficientInformation() throws NonExistentSatelliteException {
        triangulatorService.setMessage(messages[0]);
        triangulatorService.setMessage(messages[1]);

        assertThrows(NotEnoughInfoToTriangulateException.class, triangulatorService::getLocation);
    }
}
