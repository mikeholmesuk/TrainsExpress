package com.tech.service;

import com.tech.builder.StationBuilder;
import com.tech.exception.GraphNotInstantiatedException;
import com.tech.model.Route;
import com.tech.model.Station;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import org.junit.Before;
import org.junit.Test;
import uk.org.fyodor.generators.RDG;

import static org.assertj.core.api.Assertions.*;

public class RouteDataServiceTest {

    private RouteDataService routeDataService;

    @Before
    public void setup() {
        routeDataService = new RouteDataService();
        routeDataService.setRoutesGraph(new DirectedSparseMultigraph<Station, Route>());
    }

    @Test
    public void getRoutesGraphWithValidGraph_returnsGraphInstance() {
        // Given

        // When
        Graph result = routeDataService.getRoutesGraph();

        // Then
        assertThat(result).isInstanceOf(Graph.class);
    }

    @Test(expected = GraphNotInstantiatedException.class)
    public void getRoutesGraphWithNullGraphSet_throwsGraphNotInstantiatedException() {
        // Given
        routeDataService.setRoutesGraph(null);

        // When
        routeDataService.getRoutesGraph();

        // Then
        // GraphNotInstantiatedException thrown
    }

    @Test
    public void addingStation_returnsSameStation() {
        // Given
        Station randomStation = new StationBuilder().build();

        // When
        Station result = routeDataService.addStation(randomStation);

        // Then
        assertThat(result).isInstanceOf(Station.class).isEqualTo(randomStation);
    }

    @Test
    public void getStationByValidName_returnsSameStation() {
        // Given
        Station randomStation = new StationBuilder().build();
        routeDataService.addStation(randomStation);

        // When
        Station result = routeDataService.getStationByName(randomStation.getStationName());

        // Then
        assertThat(result).isInstanceOf(Station.class).isEqualTo(randomStation);
    }

    @Test
    public void getStationByNonExistentName_returnsNull() {
        // Given
        routeDataService.addStation(new StationBuilder().build());

        // When
        Station result = routeDataService.getStationByName(RDG.string().next());

        // Then
        assertThat(result).isNull();
    }

    @Test
    public void addStationToStationRouteWithValidStations_returnsTrueForSuccess() {
        // Given
        Station fromStation = routeDataService.addStation(new StationBuilder().build());
        Station toStation = routeDataService.addStation(new StationBuilder().build());
        Integer randomTravelTime = RDG.integer(Integer.MAX_VALUE).next();

        // When
        boolean result = routeDataService.addStationToStationRoute(fromStation.getStationName(), toStation.getStationName(), randomTravelTime);

        // Then
        assertThat(result).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addStationToStationRouteWithInvalidStation_throwsIllegalArgumentException() {
        // Given
        String invalidFromStationName = RDG.string().next();
        String invalidToStationName = RDG.string().next();
        Integer randomTravelTime = RDG.integer(Integer.MAX_VALUE).next();

        // When
        boolean result = routeDataService.addStationToStationRoute(invalidFromStationName, invalidToStationName, randomTravelTime);

        // Then
        // throws IllegalArgumentException
    }
}
