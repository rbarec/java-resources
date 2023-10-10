package rbarec.fundationsjava8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorMethods {

	public static void main(String[] args) {
		// URL https://medium.com/swlh/java-collectors-and-its-20-methods-2fc422920f18

		/*
		 * It is used to accumulate elements into a list. It will create a new list (It
		 * will not change the current list).
		 */
		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 6);
		integers.stream().map(x -> x * x).collect(Collectors.toList());
		// output: [1,4,9,16,25,36,36]

		
		/*
		 * Creating set: toSet() It is used to accumulate elements into a set, It will
		 * remove all the duplicate entries.
		 */
		List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 6);
		integers2.stream().map(x -> x * x).collect(Collectors.toSet());
		// output: [1,4,9,16,25,36]
	}
}
