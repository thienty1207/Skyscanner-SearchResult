package org.example;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {
    private List<SearchResult> searchResults;

    public static void main(String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Hoen Scanner";
    }

    @Override
    public void initialize(Bootstrap<HoenScannerConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
        // Load search results from JSON files
        ObjectMapper mapper = new ObjectMapper();
        searchResults = new ArrayList<>();

        try (InputStream rentalCarsStream = getClass().getClassLoader().getResourceAsStream("rental_cars.json");
             InputStream hotelsStream = getClass().getClassLoader().getResourceAsStream("hotels.json")) {

            // Parse each file and add to searchResults list
            SearchResult[] rentalCars = mapper.readValue(rentalCarsStream, SearchResult[].class);
            SearchResult[] hotels = mapper.readValue(hotelsStream, SearchResult[].class);

            searchResults.addAll(Arrays.asList(rentalCars));
            searchResults.addAll(Arrays.asList(hotels));

        } catch (IOException e) {
            System.err.println("Failed to load search results: " + e.getMessage());
            throw e;
        }

        // Register resources
        final SearchResource searchResource = new SearchResource(searchResults);
        environment.jersey().register(searchResource);
    }
} 