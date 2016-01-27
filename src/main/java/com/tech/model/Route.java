package com.tech.model;

import com.tech.exception.InvalidTravelTimeException;

public class Route {
    final Integer travelTime;

    public Route(Integer travelTime) {
        if (travelTime < 0) {
            throw new InvalidTravelTimeException("Travel times cannot be negative numbers");
        }

        this.travelTime = travelTime;
    }

    public Integer getTravelTime() {
        return this.travelTime;
    }

    public String toString() {
        return String.valueOf(this.getTravelTime());
    }
}
