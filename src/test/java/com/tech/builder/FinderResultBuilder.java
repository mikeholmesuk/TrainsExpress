package com.tech.builder;

import com.tech.model.FinderResult;
import com.tech.model.Station;
import org.apache.commons.collections15.set.ListOrderedSet;
import uk.org.fyodor.generators.RDG;

import java.util.Set;

public class FinderResultBuilder {
    private Set<Station> visitedStations = new ListOrderedSet<>();
    private Integer totalTravelTime = RDG.integer().next();

    public FinderResultBuilder withVisitedStations(Set<Station> visitedStations) {
        this.visitedStations = visitedStations;
        return this;
    }

    public FinderResultBuilder addVisitedStation(Station visitedStation) {
        this.visitedStations.add(visitedStation);
        return this;
    }

    public FinderResultBuilder withTotalTravelTime(Integer totalTravelTime) {
        this.totalTravelTime = totalTravelTime;
        return this;
    }

    public FinderResult build() {
        return new FinderResult(this.visitedStations, this.totalTravelTime);
    }
}
