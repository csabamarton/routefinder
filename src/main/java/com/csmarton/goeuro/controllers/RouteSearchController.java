package com.csmarton.goeuro.controllers;

import com.csmarton.goeuro.model.SearchResponse;
import com.csmarton.goeuro.services.RouteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RouteSearchController {

	@Autowired
	RouteSearchService routeSearchService;

	@RequestMapping("/direct")
	public SearchResponse searchStationConnection(@RequestParam(value = "dep_sid") Integer depId,
			@RequestParam(value = "arr_sid") Integer arrId)
	{
		SearchResponse searchResponse = new SearchResponse();

		searchResponse.setArriveId(arrId);
		searchResponse.setDepartureId(depId);
		searchResponse.setConnected(routeSearchService.areTwoStationOnTheSameRoute(depId, arrId));

		return searchResponse;
	}
}
