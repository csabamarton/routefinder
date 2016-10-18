package com.csmarton.goeuro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoutesearchApplication {
	public static final String BUS_ROUTE_DATA_PATH = "busRouteData";

	public static void main(String[] args) {
		System.setProperty(BUS_ROUTE_DATA_PATH, args[0]);
		SpringApplication.run(RoutesearchApplication.class, args);
	}
}
