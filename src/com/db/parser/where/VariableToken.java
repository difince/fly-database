package com.db.parser.where;

import java.util.Stack;

import com.db.parser.where.expressions.WhereField;
import com.db.parser.where.expressions.WhereItem;

public class VariableToken extends Token{

	VariableToken(String value) {
		super(value);
	}

//	@Override
//	void mutateStackForCalculation(Stack<Double> stack, Map<String, Double> variableValues) {
//		double value = variableValues.get(this.getValue());
//		stack.push(value);
//	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack, StringBuilder output) {
		output.append(this.getValue()).append(" ");
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		WhereField field = new WhereField(getValue());
		stack.push(field);
	}

	@Override
	public String toString() {
		return String.format("VariableToken [%s]", super.toString());
	}
}
