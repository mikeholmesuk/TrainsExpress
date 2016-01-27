## Solution Overview

This is a solution for the `Trains Express` problem posed by *A Tech Compoany I was interviewing with*. The overall requirements for the problem are as follows:

    A program/function where a user can provide two station names and will then be shown the correct quickest route.

The solution has been implemented in Java and can be run either as a console application (example output shown in subsequent sections) or simply through running the unit tests. Maven has been used for dependency management and the solution has been written using IntelliJ IDEA (although there is nothing preventing anyone from running with the IDE of your choice). Java 8 is required as some parts of the code use lambda expressions and Java streams.

The application uses an in-memory graph instance to store stations and the weighted routes between them, and uses an implementation of Dijkstra's algorithm to find the quickest travel time between them. Whilst undoubtedly not the only solution to the problem, it was implemented as a graph because the author considered it an interesting approach (and to be honest, these tech tests can be quite tedious, so it's worth doing something that is relatively interesting). To implement the graph this solution uses two main libraries, JUNG and Hipster4J.

Currently the solution only provides path calculation using Dijkstra's algorithm, although this has been implemented as a Strategy coupled with the Factory pattern - and as such, additional algorithms could be implemented relatively easily as long as they implement the `RouteFinder` interface and amend the `RouteFinderFactory` logic to return an appropriate concrete instance.

## CLI example

Running the program will start a command line application with the data loaded as part of the problem outline. The application will ask for two stations to be input by the user and will print out the total travel time required as well as the route which is required for the journey. An example of the program in action is shown below:

        TRAINS EXPRESS
        --------------

            1: Get quickest route between 2 stations
            X: Exit the system

        1
        Please enter your starting station:
        A
        Please enter your destination station:
        B
        Total Travel Time: 	3 minutes
        Full Route: 		A -> B

        TRAINS EXPRESS
        --------------

            1: Get quickest route between 2 stations
            X: Exit the system

        1
        Please enter your starting station:
        A
        Please enter your destination station:
        E
        Total Travel Time: 	13 minutes
        Full Route: 		A -> B -> C -> E

        TRAINS EXPRESS
        --------------

            1: Get quickest route between 2 stations
            X: Exit the system

## Tests

Tests are implemented using JUnit using AssertJ for more fluid assertions. The Builder pattern has been implemented to generate models used as part of the tests. Random data is used everywhere except where it is explicitly required not to be for the purpose of the test at hand (so for example, when setting an exact travel time). Random data is implemented using the rather excellent Fyodor library.