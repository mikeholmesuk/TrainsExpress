package com.tech.service;

import com.tech.exception.GraphNotInstantiatedException;
import com.tech.model.Route;
import com.tech.model.Station;
import edu.uci.ics.jung.graph.Graph;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class RouteDataService {
    private Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private Graph routesGraph;

    public void setRoutesGraph(Graph routesGraph) {
        this.routesGraph = routesGraph;
    }

    public Graph getRoutesGraph() {
        if (this.routesGraph != null) {
            return this.routesGraph;
        }
        else {
            throw new GraphNotInstantiatedException("Routes graph is NULL. Have you called `setRoutesGraph()` with a valid graph?");
        }
    }

    public Station addStation(Station station) {
        routesGraph.addVertex(station);
        return station;
    }

    public Station getStationByName(String stationName) {
        log.fine("Getting station details for " + stationName);

        try {
            return (Station) routesGraph.getVertices().stream()
                    .filter(Station.class::isInstance)
                    .filter(s -> ((Station) s).getStationName().equalsIgnoreCase(stationName))
                    .findFirst().get();
        }
        catch (NoSuchElementException nse) {
            log.fine("No station found with name of " + stationName);
            return null;
        }
    }

    public boolean addStationToStationRoute(String fromStation, String toStation, Integer travelTime) {
        return routesGraph.addEdge(new Route(travelTime), getStationByName(fromStation), getStationByName(toStation));
    }
}
