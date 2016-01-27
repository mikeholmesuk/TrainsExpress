package com.tech.routing;

import com.tech.builder.RouteBuilder;
import com.tech.builder.StationBuilder;
import com.tech.model.FinderResult;
import com.tech.model.Route;
import com.tech.model.Station;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class DijkstraRouteFinderTest {

    Graph routeGraph;
    RouteFinder routeFinder;

    @Before
    public void setup() {
        routeGraph = new DirectedSparseMultigraph<Station, Route>();
        routeFinder = new DijkstraRouteFinder();
    }

    @Test
    public void singleHopJourney_returnsResultWithSameTotalTravelTime() {
        // Given
        Station startStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        Route randomRoute = new RouteBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(randomRoute, startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getTotalTravelTime()).isEqualTo(randomRoute.getTravelTime());
    }

    @Test
    public void singleHopJourney_returnsResultWithCorrectVisitedStationsCount() {
        // Given
        Station startStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        Route randomRoute = new RouteBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(randomRoute, startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).hasSize(2);
    }

    @Test
    public void singleHopJourney_returnsResultWithCorrectVisitedStationValues() {
        // Given
        Station startStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        Route randomRoute = new RouteBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(randomRoute, startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).containsExactly(startStation, endStation);
    }

    @Test
    public void multiHopJourney_returnsResultWithAggregatedTotalTravelTime() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        Route firstHopRoute = new RouteBuilder().build();
        Route secondHopRoute = new RouteBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(firstHopRoute, startStation, middleStation);
        routeGraph.addEdge(secondHopRoute, middleStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getTotalTravelTime()).isEqualTo(firstHopRoute.getTravelTime() + secondHopRoute.getTravelTime());
    }

    @Test
    public void multiHopJourney_returnsResultWithCorrectVisitedStationsCount() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().build(), middleStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).hasSize(3);
    }

    @Test
    public void multiHopJourney_returnsResultWithCorrectVisitedStationValues() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().build(), middleStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).containsExactly(startStation, middleStation, endStation);
    }

    @Test
    public void multiPathsWithDirectBeingQuickest_returnsCorrectDirectTravelTime() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(10).build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(10).build(), middleStation, endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getTotalTravelTime()).isEqualTo(5);
    }

    @Test
    public void multiPathsWithDirectBeingQuickest_returnsCorrectDirectVisitedStationsCount() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(10).build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(10).build(), middleStation, endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).hasSize(2);
    }

    @Test
    public void multiPathsWithDirectBeingQuickest_returnsCorrectDirectVisitedStationValues() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(10).build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(10).build(), middleStation, endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).containsExactly(startStation, endStation);
    }

    @Test
    public void multiPathsWithMultiHopBeingQuickest_returnsCorrectAggregatedTravelTime() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), middleStation, endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(20).build(), startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getTotalTravelTime()).isEqualTo(10);
    }

    @Test
    public void multiPathsWithMultiHopBeingQuickest_returnsCorrectVisitedStationsCount() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), middleStation, endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(20).build(), startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).hasSize(3);
    }

    @Test
    public void multiPathsWithMultiHopBeingQuickest_returnsCorrectVisitedStationValues() {
        // Given
        Station startStation = new StationBuilder().build();
        Station middleStation = new StationBuilder().build();
        Station endStation = new StationBuilder().build();
        routeGraph.addVertex(startStation);
        routeGraph.addVertex(middleStation);
        routeGraph.addVertex(endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), startStation, middleStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(5).build(), middleStation, endStation);
        routeGraph.addEdge(new RouteBuilder().withTravelTime(20).build(), startStation, endStation);

        // When
        FinderResult result = routeFinder.getQuickestRoute(routeGraph, startStation, endStation);

        // Then
        assertThat(result.getVisitedStations()).containsExactly(startStation, middleStation, endStation);
    }
}
