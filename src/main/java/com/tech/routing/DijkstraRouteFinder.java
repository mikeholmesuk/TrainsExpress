package com.tech.routing;

import com.tech.model.Route;
import com.tech.model.FinderResult;
import com.tech.model.Station;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.set.ListOrderedSet;

import java.util.Set;

public class DijkstraRouteFinder implements RouteFinder {

    Transformer<Route, Integer> travelTimeTransformer = new Transformer<Route, Integer>() {

        @Override
        public Integer transform(Route route) {
            return route.getTravelTime();
        }
    };

    private Integer calculateTotalTravelTime(DijkstraShortestPath shortestPathAlgorithm, Station fromStation, Station toStation) {
        return shortestPathAlgorithm.getDistance(fromStation, toStation).intValue();
    }

    private Set<Station> calculateVisitedStations(Graph graph, Station fromStation, Station toStation, DijkstraShortestPath<Station, Route> shortestPathAlgorithm) {
        Set<Station> visitedStations = new ListOrderedSet<>();
        shortestPathAlgorithm.getPath(fromStation, toStation)
                .stream()
                .forEach(r ->
                        graph.getIncidentVertices(r).stream().forEach(s -> visitedStations.add((Station) s)));
        return visitedStations;
    }

    public FinderResult getQuickestRoute(Graph graph, Station fromStation, Station toStation) {

        DijkstraShortestPath<Station, Route> shortestPathAlgorithm = new DijkstraShortestPath(graph, travelTimeTransformer);

        Set<Station> visitedStations = calculateVisitedStations(graph, fromStation, toStation, shortestPathAlgorithm);

        Integer totalTravelTime = calculateTotalTravelTime(shortestPathAlgorithm, fromStation, toStation);

        return new FinderResult(visitedStations, totalTravelTime);
    }
}
