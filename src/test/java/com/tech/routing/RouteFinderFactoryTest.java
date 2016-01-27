package com.tech.routing;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class RouteFinderFactoryTest {

    private RouteFinderFactory routeFinderFactory;

    @Before
    public void setup() {
        routeFinderFactory = new RouteFinderFactory();
    }

    @Test
    public void getRouteFinderNoParam_returnsDijkstraAsDefault() {
        // Given

        // When
        RouteFinder routeFinderResult = routeFinderFactory.getRouteFinder();

        // Then
        assertThat(routeFinderResult).isInstanceOf(DijkstraRouteFinder.class);
    }

    @Test
    public void getRouteFinderDijkstraParam_returnsDijkstraRouteFinder() {
        // Given
        RouteFinder.algorithm algorithm = RouteFinder.algorithm.DIJKSTRA;

        // When
        RouteFinder routeFinderResult = routeFinderFactory.getRouteFinder(algorithm);

        // Then
        assertThat(routeFinderResult).isInstanceOf(DijkstraRouteFinder.class);
    }
}
