package com.db.parser.where;

import java.util.List;
import java.util.Stack;

import com.db.parser.where.expressions.WhereItem;

class ParenthesisToken extends Token {

	ParenthesisToken(String value) {
		super(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ParenthesisToken) {
			final ParenthesisToken t = (ParenthesisToken) obj;
			return t.getValue().equals(this.getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	boolean isOpen() {
		return getValue().equals("(") || getValue().equals("[")
				|| getValue().equals("{");
	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack,
			List<Token> output) {
		if (this.isOpen()) {
			operatorStack.push(this);
		} else {
			Token next;
			while ((next = operatorStack.peek()) instanceof OperatorToken
					|| (next instanceof ParenthesisToken && !((ParenthesisToken) next).isOpen())) {
				output.add(operatorStack.pop());
			}
			operatorStack.pop();
		}
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return String.format("ParenthesisToken [%s]", super.toString());
	}
}
