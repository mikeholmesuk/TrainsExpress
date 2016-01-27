package com.tech.builder;

import com.tech.model.Station;
import uk.org.fyodor.generators.RDG;

public class StationBuilder {
    private String stationName = RDG.string().next();

    public StationBuilder withStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public Station build() {
        return new Station(this.stationName);
    }
}
