package com.disys.analyzer.charts;

import java.util.*;

public class GFG {
	public static void main(String[] args)
	{

		// Create a HashMap
		HashMap<String, Object>
			map = new HashMap<>();

		// Populate the HashMap
		map.put("DOB", "Geeks");
		map.put("SSN", "ForGeeks");		

		// Get the key to be removed
		String keyToBeRemoved = "SSN";

		// Print the initial HashMap
		System.out.println("Original HashMap: "
						+ map);

		// Get the iterator over the HashMap
		Iterator<Map.Entry<String, Object> >
			iterator = map.entrySet().iterator();

		// Iterate over the HashMap
		while (iterator.hasNext()) {

			// Get the entry at this iteration
			Map.Entry<String, Object>
				entry
				= iterator.next();

			// Check if this key is the required key
			if ("SSN" == entry.getKey()) {

				// Remove this entry from HashMap
				iterator.remove();
			}
		}

		// Print the modified HashMap
		System.out.println("Modified HashMap: "
						+ map);
	}
}
