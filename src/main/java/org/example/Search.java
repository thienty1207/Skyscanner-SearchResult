package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    @JsonProperty
    private String city;

    public Search() {
        // Jackson deserialization
    }

    public Search(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
} 