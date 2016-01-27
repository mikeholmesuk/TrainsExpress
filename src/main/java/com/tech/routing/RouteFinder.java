package com.tech.routing;

import com.tech.model.FinderResult;
import com.tech.model.Station;
import edu.uci.ics.jung.graph.Graph;

/**
 * Created by mikeholmes on 21/12/2015.
 */
public interface RouteFinder {
    enum algorithm {
        DIJKSTRA
    }

    public FinderResult getQuickestRoute(Graph graph, Station fromStation, Station toStation);
}
