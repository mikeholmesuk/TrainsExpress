package com.tech.builder;

import com.tech.model.Route;
import uk.org.fyodor.generators.RDG;

public class RouteBuilder {
    private Integer travelTime = RDG.integer(Integer.MAX_VALUE).next();

    public RouteBuilder withTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
        return this;
    }

    public Route build() {
        return new Route(this.travelTime);
    }
}
