package listiteration;

import java.util.ArrayList;
import java.util.Iterator;

public class ListIterateExample {

	public static void main(String[] args) {
		var countries = new ArrayList<String>();
		countries.add("Germany");
		countries.add("France");
		countries.add("Italy");
		countries.add("Spain");

		// Basic loop
		for (int i = 0; i < countries.size(); i++) {
			String country = countries.get(i);
			printItemList(country);
		}

		// foreach
		for (String country : countries) {
			printItemList(country);
		}

		// Basic loop with iterator
		for (Iterator<String> it = countries.iterator(); it.hasNext();) {
			String country = it.next();
			printItemList(country);
		}

		// Iterator with while loop
		Iterator<String> it = countries.iterator();
		while (it.hasNext()) {
			String color = it.next();
			printItemList(color);
		}

		// JDK 8 streaming example lambda expression
		countries.stream().forEach(country -> printItemList(country));

		// JDK 8 streaming example method reference
		countries.stream().forEach(ListIterateExample::printItemList);

		// JDK 8 for each with lambda
		countries.forEach(country -> printItemList(country));

		// JDK 8 for each
		countries.forEach(ListIterateExample::printItemList);
	}

	private static void printItemList(String country) {
		System.out.println("country: " + country);
	}

}
