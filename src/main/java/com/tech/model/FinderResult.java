package com.tech.model;


import org.apache.commons.lang.StringUtils;

import java.util.Set;

public class FinderResult {
    private final Set<Station> visitedStations;
    private final Integer totalTravelTime;

    public FinderResult(Set<Station> visitedStations, Integer totalTravelTime) {
        this.visitedStations = visitedStations;
        this.totalTravelTime = totalTravelTime;
    }

    public Set<Station> getVisitedStations() {
        return this.visitedStations;
    }

    public Integer getTotalTravelTime() {
        return this.totalTravelTime;
    }

    public String toString() {
        return "Total Travel Time: \t" + getTotalTravelTime() + " minutes\n" +
                "Full Route: \t\t" + StringUtils.join(getVisitedStations(), " -> ");
    }
}
