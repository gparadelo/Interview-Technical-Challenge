package gp.interviewtechnicalchallenge.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocationTests {

    @Test
    void canGetDistancePositive() {
        Location l1 = new Location(3, 7);
        Location l2 = new Location(6, 11);

        assertEquals(5, l1.distanceTo(l2));
    }

    @Test
    void canGetDistanceOpposite() {
        Location l1 = new Location(3, 4);
        Location l2 = new Location(-3, -4);

        assertEquals(10, l1.distanceTo(l2));
    }


    @Test
    void canGetDistanceMirrored() {
        Location l1 = new Location(5, 5);
        Location l2 = new Location(-5, 5);

        assertEquals(10, l1.distanceTo(l2));
    }

    @Test
    void canGetSlope() {
        Location l1 = new Location(1, 1);
        Location l2 = new Location(-1, 1);
        Location l3 = new Location(2, 2);
        Location l4 = new Location(3, 2);

        assertEquals(0, Location.getSlope(l1, l2));
        assertEquals(1, Location.getSlope(l1, l3));
        assertEquals(0.5, Location.getSlope(l1, l4));
    }

    @Test
    void canCheckCollinear() {
        Location l1 = new Location(1, 1);
        Location l2 = new Location(-1, 1);
        Location l3 = new Location(2, 2);
        Location l4 = new Location(3, 3);
        Location l5 = new Location(0, 0);

        assertTrue(Location.collinear(List.of(l1)));
        assertTrue(Location.collinear(List.of(l1, l2)));
        assertTrue(Location.collinear(List.of(l1, l3, l4)));
        assertTrue(Location.collinear(List.of(l1, l3, l4, l5)));
        assertFalse(Location.collinear(List.of(l1, l2, l4)));

    }
}
