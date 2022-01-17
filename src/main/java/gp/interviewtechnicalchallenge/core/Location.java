package gp.interviewtechnicalchallenge.core;

import java.util.List;
import java.util.Objects;

import static gp.interviewtechnicalchallenge.util.Math.square;
import static java.lang.Math.sqrt;

public class Location {

    private final double xCoordinate;

    private final double yCoordinate;

    public Location(double x, double y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public double distanceTo(Location secondLocation) {
        double xDistance = getXCoordinate() - secondLocation.getXCoordinate();
        double yDistance = getYCoordinate() - secondLocation.getYCoordinate();

        return sqrt(square(xDistance) + square(yDistance));
    }

    public static boolean collinear(List<Location> locations) {
        if (locations.size() < 3)
            return true;
        double prevSlope = getSlope(locations.get(1), locations.get(0));

        for (int i = 2; i < locations.size(); i++) {
            Location previous = locations.get(i - 1);
            Location current  = locations.get(i);
            double slope = getSlope(current, previous);
            if (slope != prevSlope)
                return false;
        }

        return true;
    }

    public static double getSlope(Location location, Location location2) {
        return (location.getYCoordinate() - location2.getYCoordinate()) /
                (location.getXCoordinate() - location2.getXCoordinate());
    }

    @Override
    public String toString() {
        return "Location{" + "xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.xCoordinate, xCoordinate) == 0 && Double.compare(location.yCoordinate, yCoordinate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }
}
