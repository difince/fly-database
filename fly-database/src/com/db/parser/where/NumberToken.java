package com.db.parser.where;

import java.util.Stack;

import com.db.parser.where.expressions.WhereItem;
import com.db.parser.where.expressions.WhereValue;

public class NumberToken extends Token {

	private final double doubleValue;

	NumberToken(String value) {
		super(value);
		this.doubleValue = Double.parseDouble(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NumberToken) {
			final NumberToken t = (NumberToken) obj;
			return t.getValue().equals(this.getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack,StringBuilder output) {
		output.append(this.getValue()).append(' ');
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		WhereValue val = new WhereValue(ValueType.DOUBLE,doubleValue);
		stack.push(val);
	}
	
	@Override
	public String toString() {
		return String.format("NumberOperator [value: %s]", doubleValue, super.getValue());
	}

}
