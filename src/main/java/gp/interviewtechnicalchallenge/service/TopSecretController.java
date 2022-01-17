package gp.interviewtechnicalchallenge.service;

import gp.interviewtechnicalchallenge.core.Location;
import gp.interviewtechnicalchallenge.exception.NonExistentSatelliteException;
import gp.interviewtechnicalchallenge.exception.NotEnoughInfoToDecodeException;
import gp.interviewtechnicalchallenge.exception.NotEnoughInfoToTriangulateException;
import gp.interviewtechnicalchallenge.exception.TriangulationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TopSecretController {

    private final LocationTriangulatorService locationTriangulator;

    private final MessageDecoderService messageDecoder;

    static class TopSecretBodyDTO {
        public Message[] satellites;
    }

    static class TopSecretSplitBodyDTO {
        public double distance;
        public String[] message;
    }

    static class ResponseDTO {
        public final String message;
        public final LocationDTO position;

        private static class LocationDTO {
            public final double x, y;

            LocationDTO(Location location) {
                x = location.getXCoordinate(); y = location.getYCoordinate();
            }
        }

        public ResponseDTO(Location position, String message){
            this.position = new LocationDTO(position);
            this.message = message;
        }
    }

    @Autowired
    public TopSecretController(LocationTriangulatorService locationTriangulator, MessageDecoderService messageDecoder) {
        this.locationTriangulator = locationTriangulator;
        this.messageDecoder = messageDecoder;
    }

    @PostMapping("/topsecret")
    public ResponseDTO topSecret(@RequestBody TopSecretBodyDTO body) {
        try {
            locationTriangulator.setAllMessages(body.satellites);
            Location shipLocation = locationTriangulator.getLocation();

            messageDecoder.setAllMessages(body.satellites);
            String[] message = messageDecoder.decodeMessage();

            return new ResponseDTO(shipLocation, String.join(" ", message));
        }
        catch (NonExistentSatelliteException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown satellite name: " + e.satelliteName);
        }
        catch (NotEnoughInfoToTriangulateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough information to triangulate the position");
        }
        catch (NotEnoughInfoToDecodeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough information to decode the message");
        }
        catch (TriangulationException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error while trying to triangulate the position");
        }
    }

    @PostMapping("/topsecret_split/{satelliteName}")
    public void topSecretSplitPOST(@PathVariable String satelliteName, @RequestBody TopSecretSplitBodyDTO body) {
        Message message = new Message(satelliteName, body.distance, body.message);
        try {
            locationTriangulator.setMessage(message);
            messageDecoder.setMessage(message);
        } catch (NonExistentSatelliteException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown satellite name: " + e.satelliteName);
        }
    }

    @GetMapping("/topsecret_split")
    public ResponseDTO topSecretSplitGET() {
        try {
            Location shipLocation = locationTriangulator.getLocation();
            String[] message = messageDecoder.decodeMessage();

            return new ResponseDTO(shipLocation, String.join(" ", message));
        }
        catch (NotEnoughInfoToTriangulateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough information to triangulate the position");
        }
        catch (NotEnoughInfoToDecodeException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough information to decode the message");
        }
        catch (TriangulationException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error while trying to triangulate the position");
        }
    }
}
