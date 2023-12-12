package com.mycompany.calculator.basic_calculator.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EquationTest {

	@Test
	void testIsRoot() {
		Equation equation = new Equation("2*x+5=17");
		assertTrue(equation.isRoot(6.0));
		assertFalse(equation.isRoot(6.1));
		assertFalse(equation.isRoot(7.0));
		
		equation = new Equation("-1.3*5/x=1.2");
		assertTrue(equation.isRoot(-5.41667));
		assertFalse(equation.isRoot(-5.0));
		assertFalse(equation.isRoot(-2.4));
		
		equation = new Equation("2*(x+5+x)+5=10");
		assertTrue(equation.isRoot(-1.25));
		assertFalse(equation.isRoot(-1.0));
		assertFalse(equation.isRoot(-1.2));
	}

}
