package com.csmarton.goeuro.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

import static com.csmarton.goeuro.RoutesearchApplication.BUS_ROUTE_DATA_PATH;

@Component
public class RouteSearchService {
	private Map<Integer, List<Integer>> routes;
	public Map<Integer, List<Integer>> reverseMap;

	public boolean areTwoStationOnTheSameRoute(int first, int second)
	{
		List<Integer> routesOnStation1 = reverseMap.get(first);

		if (routesOnStation1 == null) {
			return false;
		}
		List<Integer> routesOnStation2 = reverseMap.get(second);

		if (routesOnStation2 == null) {
			return false;
		}

		return !Collections.disjoint(routesOnStation1, routesOnStation2);
	}

	@PostConstruct
	public void init() throws IOException
	{
		System.out.println("RouteSearchService PostConstruct method!");
		String busDataPath = System.getProperty(BUS_ROUTE_DATA_PATH);

		if (busDataPath != null) {
			File file = new File(busDataPath);
			Scanner in = new Scanner(file);

			int numOfRoutes = in.nextInt();

			routes = Maps.newHashMapWithExpectedSize(numOfRoutes);

			IntStream.range(0, numOfRoutes).forEach(i -> {
				Integer busRouteId = in.nextInt();

				String[] stationIdsInString = in.nextLine().trim().split(" ");
				List<Integer> stationIds = Lists
						.newArrayListWithCapacity(stationIdsInString.length);
				Arrays.stream(stationIdsInString).forEach(
						stationIdInString -> stationIds.add(Integer.valueOf(stationIdInString)));

				routes.put(busRouteId, stationIds);
			});

			reverseMap = MapReversingProcessor.reverseMap(routes);
		}
	}
}
