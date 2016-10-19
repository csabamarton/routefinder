# routefinder

It is a simple Spring Boot project. The program provides a REST endpoint, which will check whether two bus station is on the same bus route or not.

Therefore the request requires two parameter: dep_sid and arr_sid. They are the ids of the requested stations.
Here is an example for a request: http://localhost:8088/api/direct?dep_sid=2&arr_sid=3


The project has one Controller and one service. In the service (RouteSearchService) there is an init method what has to load the bus routes and turn it into a new (Integer -> list<Integer>) map: stationid -> busroutesids.

The service is Singleton so the PostConstructor annotated init method will run only the server restart.

Maybe it is better to make a ApplicationListener and move the init method logic into it, or create another REST endpoint for it.

The project has tests on two level: on controller and service.
