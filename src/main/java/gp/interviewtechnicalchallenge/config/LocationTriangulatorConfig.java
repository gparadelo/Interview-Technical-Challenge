package gp.interviewtechnicalchallenge.config;

import gp.interviewtechnicalchallenge.core.Location;
import gp.interviewtechnicalchallenge.core.LocationTriangulator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class LocationTriangulatorConfig {

    private final Map<String, Location> satelliteLocations;

    private final List<String> satelliteNames;

    public LocationTriangulatorConfig(Map<String, Location> satelliteLocations, List<String> satelliteNames) {
        this.satelliteLocations = satelliteLocations;
        this.satelliteNames = satelliteNames;
    }


    @Bean
    public LocationTriangulator locationTriangulator() {
        Location s1 = satelliteLocations.get(satelliteNames.get(0));
        Location s2 = satelliteLocations.get(satelliteNames.get(1));
        Location s3 = satelliteLocations.get(satelliteNames.get(2));

        return new LocationTriangulator(s1, s2, s3);
    }
}
