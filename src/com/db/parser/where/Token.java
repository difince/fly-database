package com.db.parser.where;

import java.util.Stack;

import com.db.parser.where.expressions.WhereItem;

public abstract class Token {
	private final String value;

	Token(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public abstract void mutateStackForInfixTranslation(Stack<Token> operatorStack,
			StringBuilder output);
	
	public abstract void mutateStackForCalculation(Stack<WhereItem> stack);
	
	@Override
	public String toString() {
		return String.format("value : %s", value);
	}
}
