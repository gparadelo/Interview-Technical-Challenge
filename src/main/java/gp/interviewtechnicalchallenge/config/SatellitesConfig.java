package gp.interviewtechnicalchallenge.config;

import gp.interviewtechnicalchallenge.core.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SatellitesConfig {

    @Bean
    List<String> satelliteNames() {
        return List.of("kenobi", "skywalker", "sato");
    }

    @Bean
    Map<String, Location> satelliteLocations() {
        Map<String, Location> satellitePositionMap = new HashMap<>();
        satellitePositionMap.put(satelliteNames().get(0), new Location(-500, -200));
        satellitePositionMap.put(satelliteNames().get(1), new Location(100, -100));
        satellitePositionMap.put(satelliteNames().get(2), new Location(500, 100));

        return satellitePositionMap;
    }
}
