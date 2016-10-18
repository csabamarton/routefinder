package com.csmarton.goeuro.controllers;

import com.csmarton.goeuro.services.RouteSearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RouteSearchControllerTest {
	private MockMvc mockMvc;

	@Mock
	RouteSearchService routeSearchService;

	@InjectMocks
	RouteSearchController routeSearchController;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(routeSearchController).build();
	}

	@Test
	public void searchRequestShouldReturnWithStatusOk() throws Exception
	{

		Integer arriveStationId = 2;
		Integer departureStationId = 3;

		when(routeSearchService.areTwoStationOnTheSameRoute(departureStationId, arriveStationId))
				.thenReturn(true);

		mockMvc.perform(get("/api/direct").param("dep_sid", departureStationId.toString())
				.param("arr_sid", arriveStationId.toString())).andExpect(status().isOk());
	}

	@Test
	public void searchRequestShouldReturnWithStatusOkAndValidJson() throws Exception
	{

		Integer arriveStationId = 2;
		Integer departureStationId = 3;

		when(routeSearchService.areTwoStationOnTheSameRoute(departureStationId, arriveStationId))
				.thenReturn(true);

		mockMvc.perform(get("/api/direct").param("dep_sid", departureStationId.toString())
				.param("arr_sid", arriveStationId.toString())).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("dep_sid").value(departureStationId))
				.andExpect(jsonPath("arr_sid").value(arriveStationId))
				.andExpect(jsonPath("direct_bus_route").isBoolean());
	}
}