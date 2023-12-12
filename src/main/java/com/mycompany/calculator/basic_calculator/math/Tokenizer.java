package com.mycompany.calculator.basic_calculator.math;

import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Tokenizer {
	
	/**
	 * Breaks a string that represents an arithmetic expression in a postfix notation into tokens and returns an array of these tokens.
	 * 
	 * @param expression a string that represents an arithmetic expression in a postfix notation
	 * @return an array of tokens
	 */
	public static String[] tokenize(String expression) {
		Objects.requireNonNull(expression);
		return Pattern.compile("\\(|\\)|(?<=\\W|^)-?\\d+(?:[\\.,]\\d+)?|(?<=\\W|^)-?[a-zA-Z]+\\d*|\\+|-|\\*|/")
				.matcher(expression)
				.results()
				.map(MatchResult::group)
				.toArray(String[]::new);
	}
}
