package gp.interviewtechnicalchallenge.core;


import gp.interviewtechnicalchallenge.exception.TriangulationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LocationTriangulatorTests {

    @Test
    void canGetLocation() throws TriangulationException {
        Location satellite1 = new Location(-500, -200);
        Location satellite2 = new Location(100, -100);
        Location satellite3 = new Location(500, 100);
        Location ship       = new Location(150, 300);

        LocationTriangulator triangulator = new LocationTriangulator(satellite1, satellite2, satellite3);
        assertEquals(ship, triangulator.getLocation(ship.distanceTo(satellite1), ship.distanceTo(satellite2),
                ship.distanceTo(satellite3)));
    }

    @Test
    void throwsExceptionWhenSatellitesOverlap() {
        Location satellite1 = new Location(100, -100);
        Location satellite2 = new Location(100, -100);
        Location satellite3 = new Location(500, 100);
        assertThrows(IllegalArgumentException.class, () -> new LocationTriangulator(satellite1, satellite2, satellite3));
    }

    @Test
    void throwsExceptionWhenSatellitesAreCollinear() {
        Location satellite1 = new Location(100, -200);
        Location satellite2 = new Location(100, -100);
        Location satellite3 = new Location(100, 100);
        assertThrows(IllegalArgumentException.class, () -> new LocationTriangulator(satellite1, satellite2, satellite3));
    }

    @Test
    void throwsExceptionWhenSendingLessThanThreeDistances() {
        Location satellite1 = new Location(-500, -200);
        Location satellite2 = new Location(100, -100);
        Location satellite3 = new Location(500, 100);
        Location ship       = new Location(150, 300);

        LocationTriangulator triangulator = new LocationTriangulator(satellite1, satellite2, satellite3);
        assertThrows(TriangulationException.class,
                () -> triangulator.getLocation(ship.distanceTo(satellite1), ship.distanceTo(satellite2)));
    }

    @Test
    void throwsExceptionWhenDistancesAreInvalid() {
        Location satellite1 = new Location(100, 0);
        Location satellite2 = new Location(-100, 0);
        Location satellite3 = new Location(500, 500);
        Location ship       = new Location(150, 300);

        LocationTriangulator triangulator = new LocationTriangulator(satellite1, satellite2, satellite3);
        assertThrows(TriangulationException.class, () -> triangulator.getLocation(100, 50, 700));
        assertThrows(TriangulationException.class, () -> triangulator.getLocation(-ship.distanceTo(satellite1),
                ship.distanceTo(satellite2), ship.distanceTo(satellite3)));
    }
}
