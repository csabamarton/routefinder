package com.csmarton.goeuro.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.csmarton.goeuro.RoutesearchApplication.BUS_ROUTE_DATA_PATH;
import static com.google.common.truth.Truth.assertWithMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteSearchServiceTest {

	@Autowired
	RouteSearchService routeSearchService;

	@Test
	public void shouldProcessTheBusRouteDataAfterThePathHasBeenSet() throws IOException
	{
		System.setProperty(BUS_ROUTE_DATA_PATH,
				this.getClass().getResource("/busroutes").getFile());

		routeSearchService.init();

		assertWithMessage("Station map has not been created").that(routeSearchService.reverseMap)
				.isNotEmpty();
	}

	@Test
	public void givenTwoStationOnARoadShouldGetBackTrue() throws IOException
	{
		System.setProperty(BUS_ROUTE_DATA_PATH,
				this.getClass().getResource("/busroutes").getFile());

		routeSearchService.init();

		boolean isOnTheSameRoute = routeSearchService.areTwoStationOnTheSameRoute(153, 12);

		assertWithMessage("Station map is not valid").that(isOnTheSameRoute).isTrue();
	}

	@Test
	public void givenTwoStationOnDifferentRoadShouldGetBackFalse() throws IOException
	{
		System.setProperty(BUS_ROUTE_DATA_PATH,
				this.getClass().getResource("/busroutes").getFile());

		routeSearchService.init();

		boolean isOnTheSameRoute = routeSearchService.areTwoStationOnTheSameRoute(153, 142);

		assertWithMessage("Station map is not valid").that(isOnTheSameRoute).isFalse();
	}
}