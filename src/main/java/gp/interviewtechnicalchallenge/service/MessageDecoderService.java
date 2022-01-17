package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.core.MessageDecoder;
import gp.interviewtechnicalchallenge.exception.NotEnoughInfoToDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageDecoderService extends TopSecretService {

    @Autowired
    public MessageDecoderService(List<String> satelliteNames) {
        super(satelliteNames);
    }

    public String[] decodeMessage() throws NotEnoughInfoToDecodeException {
        Stream<String[]> messages = getAllMessages().stream().filter(Predicate.not(Objects::isNull)).map(message -> message.message);
        String[] message = MessageDecoder.getMessage(messages.collect(Collectors.toList()).toArray(String[][]::new));
        if (Arrays.asList(message).contains(""))
            throw new NotEnoughInfoToDecodeException();
        return message;

    }
}
