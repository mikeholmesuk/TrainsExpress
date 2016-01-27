package com.tech.model;

import com.tech.builder.RouteBuilder;
import com.tech.exception.InvalidTravelTimeException;
import org.junit.Test;
import uk.org.fyodor.generators.RDG;

import static org.assertj.core.api.Assertions.*;

public class RouteTest {

    @Test
    public void createRouteWithPositiveNumber_returnsNewRoute() {
        // Given
        Integer positiveNumber = RDG.integer(Integer.MAX_VALUE).next();

        // When
        Route route = new RouteBuilder().withTravelTime(positiveNumber).build();

        // Then
        assertThat(route).isInstanceOf(Route.class);
    }

    @Test(expected = InvalidTravelTimeException.class)
    public void createRouteWithNegativeNumber_throwsInvalidTravelTimeException() {
        // Given
        Integer negativeNumber = -1;

        // When
        Route route = new RouteBuilder().withTravelTime(negativeNumber).build();

        // Then
        // InvalidTravelTimeException thrown
    }
}
