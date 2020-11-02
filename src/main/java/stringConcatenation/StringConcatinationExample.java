package stringConcatination;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

public class StringConcatinationExample {

	public static void main(String[] args) {
		String greeting = "Hi";
		String person = "neighbor";
		String location = "neighborhood";

		// The + Operator
		String plusConcat = greeting + " " + person + "! Welcome to the " + location + "!";

		// StringBuilder
		String stringBuilderConcat = new StringBuilder().append(greeting).append(" ").append(person)
				.append("! Welcome to the ").append(location).append("!").toString();

		// StringBuffer
		String stringBufferConcat = new StringBuffer().append(greeting).append(" ").append(person)
				.append("! Welcome to the ").append(location).append("!").toString();

		// String.concat()
		String stringConcatConcat = greeting.concat(" ").concat(person).concat("! Welcome to the ").concat(location)
				.concat("!");

		// String.format()
		String stringFormatConcat = String.format("%s %s! Welcome to the %s!", greeting, person, location);

		// String.join()
		String stringJoinConcat = String.join("" /* delimiter */, greeting, " ", person, "! Welcome to the ", location,
				"!");

		// StringJoiner
		String stringJoiner = new StringJoiner("" /* delimiter */).add(greeting).add(" ").add(person)
				.add("! Welcome to the ").add(location).add("!").toString();

		// Streams
		String streamsConcat = Arrays.asList(greeting, " ", person, "! Welcome to the ", location, "!").stream()
				.collect(Collectors.joining());

		// Apache Commons’ StringUtils
		String apacheStringUtilsConcat = StringUtils.join(greeting, " ", person, "! Welcome to the ", location, "!");
		
		// Google Guava’s Joiner
		String guavaJoinerConcat = Joiner.on("" /*delimiter*/)
		    .join(greeting, " ", person, "! Welcome to the ", location, "!");

	}

}
