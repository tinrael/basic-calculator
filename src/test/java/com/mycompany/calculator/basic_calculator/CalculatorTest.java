package com.mycompany.calculator.basic_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	private final static double DELTA = 1e-9;

	@Test
	void testEvaluatePostfix() {
		assertEquals(12.0, Calculator.evaluatePostfix(new String[]{"12"}), DELTA);
		assertEquals(2.5, Calculator.evaluatePostfix(new String[]{"5", "2", "/"}), DELTA);
		assertEquals(9.0, Calculator.evaluatePostfix(new String[]{"2", "1", "+", "3", "*"}), DELTA);
		assertEquals(6.6, Calculator.evaluatePostfix(new String[]{"4", "13", "5", "/", "+"}), DELTA);
		assertEquals(21.5454545455, 
				Calculator.evaluatePostfix(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}),
				DELTA);
	}
	
	@Test
	void testConvertInfixToPostfix() {
		assertArrayEquals(new String[]{"3"}, 
				Calculator.convertInfixToPostfix(new String[]{"3"}));
		
		assertArrayEquals(new String[]{"2", "1", "+", "3", "*"}, 
				Calculator.convertInfixToPostfix(new String[]{"(", "2", "+", "1", ")", "*", "3"}));
		
		assertArrayEquals(new String[]{"4", "13", "5", "/", "+"}, 
				Calculator.convertInfixToPostfix(new String[]{"4", "+", "13", "/", "5"}));
		
		assertArrayEquals(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}, 
				Calculator.convertInfixToPostfix(new String[]{"10", "*", "(", "6", "/", "(", "(", "9", "+", "3", ")", "*", "-11", ")", ")", "+", "17", "+", "5"}));
		
		assertArrayEquals(new String[]{"8", "2", "/", "4", "*"}, 
				Calculator.convertInfixToPostfix(new String[]{"8", "/", "2", "*", "4"}));
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Calculator.convertInfixToPostfix(new String[]{"2", "+", "1", ")", "*", "3"});
		});
		assertEquals("There are mismatched parentheses.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			Calculator.convertInfixToPostfix(new String[]{"(", "2", "+", "1", "*", "3"});
		});
		assertEquals("There are mismatched parentheses.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			Calculator.convertInfixToPostfix(new String[]{"2", "+", "y"});
		});
		assertEquals("There is an unknown token.", exception.getMessage());
	}

}
