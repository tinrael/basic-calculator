package com.mycompany.calculator.basic_calculator;

import java.util.Objects;
import java.util.Stack;

public class Calculator {
	
	/**
	 * Evaluates an arithmetic expression specified in a Reverse Polish Notation and returns its value.
	 * The tokens argument must be a valid arithmetic expression in a Reverse Polish Notation.
	 * The valid operators are '+', '-', '*', and '/'.
	 * 
	 * @param tokens an array of strings that represents an arithmetic expression in a Reverse Polish Notation
	 * @return the value of the expression
	 */
	public static double evaluatePostfix(String[] tokens) {
		Objects.requireNonNull(tokens);
		
		Stack<Double> stack = new Stack<>();
		double a;
		double b;
		for (String token : tokens) {
			switch (token) {
				case "+":
					stack.push(stack.pop() + stack.pop());
					break;
				case "-":
					a = stack.pop();
					b = stack.pop();
					stack.push(b - a);
					break;
				case "*":
					stack.push(stack.pop() * stack.pop());
					break;
				case "/":
					a = stack.pop();
					b = stack.pop();
					stack.push(b / a);
					break;
				default:
					stack.push(Double.parseDouble(token));
					break;
			}
		}
		
		return stack.pop();
	}
}
