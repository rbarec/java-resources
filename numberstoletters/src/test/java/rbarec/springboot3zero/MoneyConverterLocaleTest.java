package rbarec.springboot3zero;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyConverterLocaleTest {

	@BeforeAll
	public void beforeAll() {
		System.out.println("BeforeAll");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("@BeforeEach");
	}
	
	@DisplayName("Single test successful")
	@Test
	void testSingleSuccessTest() {
		System.out.println("Success");
	}

	@Test
	@Disabled("Not implemented yet")
	void testShowSomething() {
	}
	
	@AfterAll
	public void afterAll() {
		System.out.println("@AfterAll");
	}
	
//	@Test
//	void lambdaExpressions() {
//	    List numbers = Arrays.asList(1, 2, 3);
//	    assertTrue(numbers.stream()
//	      .mapToInt(Integer::intValue)
//	      .sum() > 5, () -> "Sum should be greater than 5");
//	}
	
	@Test
	 void groupAssertions() {
	     int[] numbers = {0, 1, 2, 3, 4};
	     assertAll("numbers",
	         () -> assertEquals(numbers[0], 1),
	         () -> assertEquals(numbers[3], 3),
	         () -> assertEquals(numbers[4], 1)
	     );
	 }
}
