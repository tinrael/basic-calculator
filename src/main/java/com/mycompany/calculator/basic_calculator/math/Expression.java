package com.mycompany.calculator.basic_calculator.math;

import java.util.Objects;

import com.mycompany.calculator.basic_calculator.Calculator;

public class Expression {
	private String[] postfix;
	
	Expression(String expression) {
		Objects.requireNonNull(expression);
		this.postfix = Calculator.convertInfixToPostfix(Tokenizer.tokenize(expression));
	}
	
	public double evaluate(double x) {
		String[] tokens = new String[this.postfix.length];
		
		for (int i = 0; i < this.postfix.length; i++) {
			tokens[i] = this.postfix[i].replace("x", String.valueOf(x));
		}
		
		return Calculator.evaluatePostfix(tokens);
	}
	
	public String[] getPostfix() {
		return this.postfix;
	}
}
