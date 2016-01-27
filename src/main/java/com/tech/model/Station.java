package com.tech.model;

/**
 * Created by mikeholmes on 21/12/2015.
 */
public class Station {
    final String stationName;

    public Station(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return this.stationName;
    }

    public String toString() {
        return this.stationName;
    }
}
