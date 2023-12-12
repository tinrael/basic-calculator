package com.mycompany.calculator.basic_calculator.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TokenizerTest {

	@Test
	void testTokenize() {
		assertArrayEquals(new String[]{"5"}, 
				Tokenizer.tokenize("5"));
		assertArrayEquals(new String[]{"-5"}, 
				Tokenizer.tokenize("-5"));
		
		assertArrayEquals(new String[]{"3.5"}, 
				Tokenizer.tokenize("3.5"));
		assertArrayEquals(new String[]{"-3.5"}, 
				Tokenizer.tokenize("-3.5"));
		
		assertArrayEquals(new String[]{"2.2", "+", "1.7"}, 
				Tokenizer.tokenize("2.2+1.7"));
		
		assertArrayEquals(new String[]{"5.0", "*", "-4.3"}, 
				Tokenizer.tokenize("5.0*-4.3"));
		
		String[] expected = new String[]{"(", "2.1", "+", "1.0", ")", "*", "3.5"};
		assertArrayEquals(expected, 
				Tokenizer.tokenize("(2.1+1.0)*3.5"));
		assertArrayEquals(expected, 
				Tokenizer.tokenize("( 2.1   +1.0)* 3.5"));
		
		assertArrayEquals(new String[]{"2", "*", "x", "+", "5"}, 
				Tokenizer.tokenize("2*x+5"));
		
		assertArrayEquals(new String[]{"-1.3", "*", "5", "/", "x"}, 
				Tokenizer.tokenize("-1.3*5/x"));
		
		assertArrayEquals(new String[]{"2", "*", "x", "*", "x"}, 
				Tokenizer.tokenize("2*x*x"));
		
		assertArrayEquals(new String[]{"2", "*", "(", "x", "+", "5", "+", "x", ")", "+", "5"}, 
				Tokenizer.tokenize("2*(x+5+x)+5"));
	}

}
