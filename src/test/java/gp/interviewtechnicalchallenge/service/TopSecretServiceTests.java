package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.exception.NonExistentSatelliteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopSecretServiceTests {

    Message[] messages;

    TopSecretService topSecretService;

    @BeforeEach
    void setUp() {
        String[] names = {"kenobi", "skywalker", "sato"};
        topSecretService = new TopSecretService(List.of(names));

        Message message1 = new Message(names[0], 1, new String[]{});
        Message message2 = new Message(names[1], 2, new String[]{});
        Message message3 = new Message(names[2], 5, new String[]{});

        messages = new Message[]{message1, message2, message3};
    }

    @Test
    public void canSetAndGetAllMessages() throws NonExistentSatelliteException {
        topSecretService.setAllMessages(messages);
        assertArrayEquals(messages, topSecretService.getAllMessages().toArray());
    }

    @Test
    public void canSetAndGetSomeMessages() throws NonExistentSatelliteException {
        topSecretService.setMessage(messages[0]);
        topSecretService.setMessage(messages[1]);

        assertArrayEquals(new Message[]{messages[0], messages[1], null}, topSecretService.getAllMessages().toArray());
    }

    @Test
    public void exceptionWhenSettingNonExistentSatellite() {
        Message messageWithUnknownSatellite = new Message("solo", 0, new String[0]);
        assertThrows(NonExistentSatelliteException.class,
                () -> topSecretService.setMessage(messageWithUnknownSatellite));
    }
}
