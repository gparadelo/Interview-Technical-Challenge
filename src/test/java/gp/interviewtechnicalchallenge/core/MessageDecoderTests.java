package gp.interviewtechnicalchallenge.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageDecoderTests {

    @Test
    void canDecodeMessage() {
        String[] message1 = {"", "", "", "message"};
        String[] message2 = {"this", "", "", "message"};
        String[] message3 = {"", "is", "a", ""};

        assertArrayEquals(new String[]{"this", "is", "a", "message"},
                MessageDecoder.getMessage(message1, message2, message3));
    }

    @Test
    void canDecodeMessageWithBlanks() {
        String[] message1 = {"this", "", "a", "message"};
        String[] message2 = {"this", "", "", "message"};
        String[] message3 = {"this", "", "a", "message"};

        assertArrayEquals(new String[]{"this", "", "a", "message"},
                MessageDecoder.getMessage(message1, message2, message3));
    }

    @Test
    void canDecodeMessageWithOffset() {
        String[] message1 = {"this", "", "a", "message"};
        String[] message2 = {"", "", "", "this", "", "", "message"};
        String[] message3 = {"", "", "this", "is", "a", "message"};

        assertArrayEquals(new String[]{"this", "is", "a", "message"},
                MessageDecoder.getMessage(message1, message2, message3));
    }

    @Test
    void canDecodeMessageWithAllOffsets() {
        String[] message1 = {"", "this", "", "a", "message"};
        String[] message2 = {"", "", "", "this", "", "", "message"};
        String[] message3 = {"", "", "this", "is", "a", "message"};

        assertArrayEquals(new String[]{"this", "is", "a", "message"},
                MessageDecoder.getMessage(message1, message2, message3));
    }


    @Test
    void canDecodeEmptyArray() {
        assertArrayEquals(new String[]{}, MessageDecoder.getMessage());
    }

}
