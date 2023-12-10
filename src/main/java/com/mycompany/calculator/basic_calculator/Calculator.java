package com.mycompany.calculator.basic_calculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Calculator {
	
	/**
	 * Parses (using the shunting yard algorithm) an arithmetical expression specified in an infix notation 
	 * and returns its postfix representation.
	 * 
	 * @throws IllegalArgumentException if the tokens argument represents an invalid arithmetic expression (e.g., there are mismatched parentheses).
	 * 
	 * @param tokens an array of strings that represents an arithmetic expression in an infix notation
	 * @return an array of strings that represents an arithmetic expression in a postfix notation
	 */
	public static String[] convertInfixToPostfix(String[] tokens) {
		Objects.requireNonNull(tokens);
		
		Stack<String> operators = new Stack<>();
		List<String> output = new LinkedList<>();
		
		for (String token : tokens) {
			if (isNumber(token)) {
				output.add(token);
			} else if (isOperator(token)) {
				while (!operators.isEmpty() && !Objects.equals(operators.peek(), "(") && 
						(getOperatorPrecedence(operators.peek()) >= getOperatorPrecedence(token))) {
					output.add(operators.pop());
				}
				operators.push(token);
			} else if (Objects.equals(token, "(")) {
				operators.push(token);
			} else if (Objects.equals(token, ")")) {
				while (!operators.isEmpty() && !Objects.equals(operators.peek(), "(")) {
					output.add(operators.pop());
				}
				if (operators.isEmpty()) { // If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.
					throw new IllegalArgumentException("There are mismatched parentheses.");
				} else { // There is a left parenthesis at the top of the operator stack.
					operators.pop();
				}
			} else {
				throw new IllegalArgumentException("There is an unknown token.");
			}
		}
		
		while (!operators.isEmpty()) {
			if (!Objects.equals(operators.peek(), "(")) {
				output.add(operators.pop());
			} else {
				throw new IllegalArgumentException("There are mismatched parentheses.");
			}
		}
		
		return output.toArray(new String[output.size()]);
	}
	
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
	
	private static byte getOperatorPrecedence(String token) {
		if (isOperator(token)) {
			switch (token) {
				case "+":
				case "-":
					return 1;
				case "*":
				case "/":
					return 2;
				default:
					return -1;
			}
		} else {
			throw new IllegalArgumentException("The specified token is not an operator.");
		}
	}
	
	private static boolean isOperator(String token) {
		Objects.requireNonNull(token);
		return token.matches("[-+*/]");
	}
	
	private static boolean isNumber(String token) {
		Objects.requireNonNull(token);
		return token.matches("-?\\d+(\\.\\d+)?");
	}
}
