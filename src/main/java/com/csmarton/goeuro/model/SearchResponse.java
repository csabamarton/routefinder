package com.csmarton.goeuro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResponse
{
	public SearchResponse()
	{
	}

	@JsonProperty("dep_sid")
	int departureId;

	@JsonProperty("arr_sid")
	int arriveId;

	@JsonProperty("direct_bus_route")
	boolean connected;

	public int getDepartureId()
	{
		return departureId;
	}

	public void setDepartureId(int departureId)
	{
		this.departureId = departureId;
	}

	public int getArriveId()
	{
		return arriveId;
	}

	public void setArriveId(int arriveId)
	{
		this.arriveId = arriveId;
	}

	public boolean isConnected()
	{
		return connected;
	}

	public void setConnected(boolean connected)
	{
		this.connected = connected;
	}
}
