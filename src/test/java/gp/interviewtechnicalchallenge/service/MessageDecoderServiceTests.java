package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.exception.NonExistentSatelliteException;
import gp.interviewtechnicalchallenge.exception.NotEnoughInfoToDecodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MessageDecoderServiceTests {

    Message[] messages;

    MessageDecoderService decoderService;

    @BeforeEach
    void setUp() {
        String[] names = {"kenobi", "skywalker", "sato"};

        decoderService = new MessageDecoderService(List.of(names));

        Message message1 = new Message(names[0], 1, new String[]{"this", "", "", "message"});
        Message message2 = new Message(names[1], 2, new String[]{"", "this", "is", "", ""});
        Message message3 = new Message(names[2], 5, new String[]{"", "", "", "", "a", ""});

        messages = new Message[]{message1, message2, message3};
    }

    @Test
    public void canGetMessage() throws NonExistentSatelliteException, NotEnoughInfoToDecodeException {
        decoderService.setAllMessages(messages);
        assertArrayEquals(new String[]{"this", "is", "a", "message"}, decoderService.decodeMessage());
    }

    @Test
    public void canGetMessagePartial() throws NonExistentSatelliteException, NotEnoughInfoToDecodeException {
        decoderService.setMessage(messages[0]);
        decoderService.setMessage(messages[1]);
        decoderService.setMessage(messages[2]);

        assertArrayEquals(new String[]{"this", "is", "a", "message"}, decoderService.decodeMessage());
    }

    @Test
    public void exceptionWhenInsufficientInformation() throws NonExistentSatelliteException {
        decoderService.setMessage(messages[0]);
        decoderService.setMessage(messages[1]);

        assertThrows(NotEnoughInfoToDecodeException.class, decoderService::decodeMessage);
    }
}
