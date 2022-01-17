package gp.interviewtechnicalchallenge.core;

import gp.interviewtechnicalchallenge.exception.TriangulationException;

import java.util.Arrays;
import java.util.List;

import static gp.interviewtechnicalchallenge.util.Math.square;

public class LocationTriangulator {
// Object to calculate a location given three known locations and it's distance to it.

    private final Location location1;
    private final Location location2;
    private final Location location3;

    public LocationTriangulator(Location location1, Location location2, Location location3) throws IllegalArgumentException {
        if (location1.equals(location2) || location1.equals(location3) || location2.equals(location3)) {
            String errorMessage = String.format("All locations must be at different" +
                    "- location1: %s; location2: %s; location3: %s", location1, location2, location3);
            throw new IllegalArgumentException(errorMessage);
        }
        if (Location.collinear(List.of(location1, location2, location3))) {
            String errorMessage = String.format("Locations must not be collinear" +
                    "- location1: %s; location2: %s; location3: %s", location1, location2, location3);
            throw new IllegalArgumentException(errorMessage);
        }

        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
    }

    public Location getLocation(double... distances) throws TriangulationException {
        // Get a location based on its distances to the known locations.
        if (distances.length < 3) {
            String errorMessage = "A distance is required for all three satellites - distances: " + Arrays.toString(distances);
            throw new TriangulationException(errorMessage);
        }

        double x1 = location1.getXCoordinate();
        double x2 = location2.getXCoordinate();
        double x3 = location3.getXCoordinate();
        double y1 = location1.getYCoordinate();
        double y2 = location2.getYCoordinate();
        double y3 = location3.getYCoordinate();
        double d1 = distances[0];
        double d2 = distances[1];
        double d3 = distances[2];

        // Solve the system of linear equations to triangulate.
        double A = 2 * x2 - 2 * x1;
        double B = 2 * y2 - 2 * y1;
        double C = square(d1) - square(d2) - square(x1) + square(x2) - square(y1) + square(y2);
        double D = 2 * x3 - 2 * x2;
        double E = 2 * y3 - 2 * y2;
        double F = square(d2) - square(d3) - square(x2) + square(x3) - square(y2) + square(y3);

        double xShip = (C * E - B * F) / (A * E - B * D);
        double yShip = (C * D - A * F) / (B * D - A * E);
        Location ship = new Location(xShip, yShip);

        if (location1.distanceTo(ship) != d1 || location2.distanceTo(ship) != d2 || location3.distanceTo(ship) != d3) {
            String errorMessage = String.format("The distances to the satellites don't match a valid location " +
                            "- satellite1: %s; satellite2: %s; satellite3: %s", location1, location2, location3);
            throw new TriangulationException(errorMessage);
        }

        return new Location(xShip + 0.0,yShip + 0.0);
    }
}
