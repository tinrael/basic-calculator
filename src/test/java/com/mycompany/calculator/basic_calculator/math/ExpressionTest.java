package com.mycompany.calculator.basic_calculator.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpressionTest {
	
	private final static double DELTA = 1e-9;

	@Test
	void testEvaluate() {
		Expression expression = new Expression("2.5 +x");
		assertEquals(6.6, expression.evaluate(4.1), DELTA);
		assertEquals(5.0, expression.evaluate(2.5), DELTA);
		
		expression = new Expression("(5.4+ x ) *x - 3.0");
		assertEquals(8.2, expression.evaluate(1.6), DELTA);
		assertEquals(-9.65, expression.evaluate(-3.5), DELTA);
		
		expression = new Expression("x");
		assertEquals(1.6, expression.evaluate(1.6), DELTA);
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Expression("2+1)*3");
		});
		assertEquals("There are mismatched parentheses.", exception.getMessage());
		
		exception = assertThrows(IllegalArgumentException.class, () -> {
			new Expression("(2+1*3");
		});
		assertEquals("There are mismatched parentheses.", exception.getMessage());
	}

}
