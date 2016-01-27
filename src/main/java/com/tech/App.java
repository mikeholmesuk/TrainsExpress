package com.tech;

import com.tech.client.CLI;
import com.tech.model.Route;
import com.tech.model.Station;
import com.tech.service.RouteDataService;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

import java.util.logging.Logger;

public class App {
    final Logger logger = Logger.getLogger(App.class.getSimpleName());

    public static void main(String... args) {
        new App().startClient();
    }

    private RouteDataService initialiseRouteDataService() {
        RouteDataService routeDataService = new RouteDataService();
        routeDataService.setRoutesGraph(new DirectedSparseMultigraph<Station, Route>());

        // Add the required stations to the graph
        routeDataService.addStation(new Station("A"));
        routeDataService.addStation(new Station("B"));
        routeDataService.addStation(new Station("C"));
        routeDataService.addStation(new Station("D"));
        routeDataService.addStation(new Station("E"));
        // Set up the station routes
        routeDataService.addStationToStationRoute("A", "B", 3);
        routeDataService.addStationToStationRoute("B", "A", 3);
        routeDataService.addStationToStationRoute("A", "D", 6);
        routeDataService.addStationToStationRoute("B", "C", 7);
        routeDataService.addStationToStationRoute("C", "D", 8);
        routeDataService.addStationToStationRoute("D", "E", 9);
        routeDataService.addStationToStationRoute("E", "D", 9);
        routeDataService.addStationToStationRoute("D", "C", 9);
        routeDataService.addStationToStationRoute("D", "B", 5);
        routeDataService.addStationToStationRoute("C", "E", 3);

        return routeDataService;
    }

    private void startClient() {
        logger.info("Starting Trains Express client");

        CLI client = new CLI();

        // Pass in the DataService and the appropriate graph instance inc required data
        client.setRouteDataService(initialiseRouteDataService());

        client.start();
    }
}
