package gp.interviewtechnicalchallenge.core;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MessageDecoder {

    private MessageDecoder() {}

    public static String[] getMessage(String[]... messages) {
        // Given a collection of string arrays that may have empty values,
        // return the array of strings with a non-empty string for each position where possible.
        // Considering that the input arrays may start with an undetermined number of empty strings of offset.
        int minLength = Arrays.stream(messages).map(Array::getLength).min(Integer::compareTo).orElse(0);
        List<String> decodedMessage = new ArrayList<>(minLength);
        for (int i = 0; i < minLength; i++)
            decodedMessage.add("");

        for (int i = 1; i <= minLength; i++) {
            for (String[] message : messages) {
                if (message.length - i < 0 || "".equals(message[message.length - i]))
                    continue;
                decodedMessage.set(minLength - i, message[message.length - i]);
                break;
            }
        }

        int i = 0;
        while(i < decodedMessage.size() && decodedMessage.get(i).equals("")) i++;
        return decodedMessage.subList(i, decodedMessage.size()).toArray(String[]::new);
    }

}
