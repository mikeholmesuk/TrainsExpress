package com.tech.routing;

/**
 * Created by mikeholmes on 22/12/2015.
 */
public class RouteFinderFactory {

    public RouteFinder getRouteFinder() {
        return getRouteFinder(RouteFinder.algorithm.DIJKSTRA);
    }

    public RouteFinder getRouteFinder(RouteFinder.algorithm algorithm) {
        RouteFinder routeFinder;

        switch (algorithm) {
            case DIJKSTRA:
                routeFinder = new DijkstraRouteFinder();
                break;
            default:
                throw new RuntimeException("No suitable algorithm found to find route");
        }

        return routeFinder;
    }
}
