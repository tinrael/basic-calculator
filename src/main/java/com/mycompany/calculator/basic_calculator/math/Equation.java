package com.mycompany.calculator.basic_calculator.math;

import java.util.Objects;

public class Equation {
	private final static double DELTA = 1e-9;
	
	private Expression lhs;
	private Expression rhs;
	
	Equation(String equation) {
		Objects.requireNonNull(equation);
		String[] expressions = equation.split("=", 2);
		this.lhs = new Expression(expressions[0]);
		this.rhs = new Expression(expressions[1]);
	}
	
	public boolean isRoot(double x) {
		return ((lhs.evaluate(x) - rhs.evaluate(x)) <= DELTA);
	}
	
	public Expression getLhs() {
		return this.lhs;
	}
	
	public Expression getRhs() {
		return this.rhs;
	}
}
