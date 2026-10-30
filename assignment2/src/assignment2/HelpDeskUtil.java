package assignment2;

import java.util.List;

public class HelpDeskUtil {
	
	// Requirement A: Generic method
	// prints each item on its own line using toString()
	
	public static <T> void printAll(List<T> items) {
		if (items == null) {
			throw new IllegalArgumentException("List cannot null.");
		}
		for (T item : items) {
			System.out.println(item);
		}
	}
	
	// Requirement B: Wildcard method
	// returns total service points across all technicians in the list
	public static int sumPoints(List<? extends Technician> technicians) {
		if (technicians == null) {
			throw new IllegalArgumentException("Technicians cannot be null.");
		}
		
		int sum = 0;
		
		for (Technician tech : technicians) {
			sum += tech.getServicePoints();
		}
		
		return sum;
	}
}