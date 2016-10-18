package com.csmarton.goeuro.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class MapReversingProcessor
{
	public static Map<Integer, List<Integer>> reverseMap(Map<Integer, List<Integer>> source) {
		Map<Integer, List<Integer>> reversedMap = Maps.newHashMap();

		source.forEach((key,value) -> {
			value.forEach(reverseKey -> {
				List reverseValues = reversedMap.get(reverseKey);
				if(reverseValues == null) {
					reverseValues = Lists.newArrayList();
				}

				reverseValues.add(key);
				reversedMap.put(reverseKey, reverseValues);
			});
		});

		return reversedMap;
	}
}
