package com.tech.client;

import com.tech.model.Station;
import com.tech.routing.RouteFinder;
import com.tech.routing.RouteFinderFactory;
import com.tech.service.RouteDataService;

import java.util.Scanner;
import java.util.logging.Logger;

public class CLI {
    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private RouteDataService routeDataService;

    public void start() {
        log.fine("Starting command line client");

        Scanner scanner = new Scanner(System.in);
        String choice = null;

        while(!"x".equals(choice)) {

            outputMenu();

            choice = scanner.nextLine();

            switch(choice.toLowerCase()) {
                case "1":
                    handleQuickestRouteRequest(scanner);
                    break;
                case "x":
                    log.fine("User has selected to exit the app");
                    System.out.println("\nThanks for using the TRAINS EXPRESS app! Have a great day :)");
                    break;
                default:
                    log.info("Choice not recognised " + choice);
            }
        }
        scanner.close();
    }

    private void outputMenu() {
        System.out.println("\nTRAINS EXPRESS");
        System.out.println("--------------\n");
        System.out.println("\t1: Get quickest route between 2 stations");
        System.out.println("\tX: Exit the system\n");
    }

    private void handleQuickestRouteRequest(Scanner scanner) {
        System.out.println("Please enter your starting station: ");
        String start = scanner.nextLine();
        System.out.println("Please enter your destination station: ");
        String destination = scanner.nextLine();
        RouteFinder routeFinder = new RouteFinderFactory().getRouteFinder();
        Station fromStation, toStation;
        fromStation = routeDataService.getStationByName(start);
        toStation = routeDataService.getStationByName(destination);
        System.out.println(routeFinder.getQuickestRoute(routeDataService.getRoutesGraph(), fromStation, toStation));
    }

    public void setRouteDataService(RouteDataService routeDataService) {
        this.routeDataService = routeDataService;
    }
}
