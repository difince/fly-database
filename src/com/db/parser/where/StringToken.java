package com.db.parser.where;

import java.util.List;
import java.util.Stack;

import com.db.parser.where.expressions.WhereItem;
import com.db.parser.where.expressions.WhereValue;

public class StringToken extends Token{
	StringToken(String value) {
		super(value);
	}

//	@Override
//	void mutateStackForCalculation(Stack<Double> stack, Map<String, Double> variableValues) {
//		double value = variableValues.get(this.getValue());
//		stack.push(value);
//	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack, List<Token> output) {
		output.add(this);
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		WhereValue val = new WhereValue(ValueType.STRING,getValue().replaceAll("'", ""));
		stack.push(val);
	}
	
	@Override
	public String toString() {
		return String.format("StringToken [%s]", super.toString());
	}
}
