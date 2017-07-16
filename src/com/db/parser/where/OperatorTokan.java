package com.db.parser.where;

import java.util.List;
import java.util.Stack;

import com.db.parser.where.expressions.WhereExpression;
import com.db.parser.where.expressions.WhereField;
import com.db.parser.where.expressions.WhereItem;
import com.db.parser.where.expressions.WhereValue;
import com.db.parser.where.expressions.booleans.WhereAndGroup;
import com.db.parser.where.expressions.booleans.WhereOrGroup;
import com.db.parser.where.expressions.comparison.WhereEqualGroup;
import com.db.parser.where.expressions.comparison.WhereGreateEqualGroup;
import com.db.parser.where.expressions.comparison.WhereGreaterGroup;
import com.db.parser.where.expressions.comparison.WhereSmallerEqualGroup;
import com.db.parser.where.expressions.comparison.WhereSmallerGroup;
class OperatorToken extends Token{

	enum Operation {
		AND(1, true), 
		OR(1, true),
		GREATER(2, true),
		SMALLER(2, true),
		EQUAL(2, true),
		SMALLER_EQUAL(2, false), 
		GREATER_EQUAL(2, false),
		NO_EQUAL(2, false),
		IN(2, false),
		NOT_IN(2, false);
		
		private final int precedence;
		private final boolean leftAssociative;

		private Operation(int precedence, boolean leftAssociative) {
			this.precedence = precedence;
			this.leftAssociative = leftAssociative;
		}
	}

	static Operation getOperation(String operator) {
		switch (operator) {
		case "and":
			return Operation.AND;
		case "or":
			return Operation.OR;
		case ">":
			return Operation.GREATER;
		case "<":
			return Operation.SMALLER;
		case "=":
			return Operation.EQUAL;
		case "<=":
			return Operation.SMALLER_EQUAL;
		case ">=":
			return Operation.GREATER_EQUAL;
		case "!=":
			return Operation.NO_EQUAL;
		case "in":
			return Operation.IN;
		case "not in":
			return Operation.NOT_IN;
		default:
			return null;
		}
	}

	static boolean isOperator(String s) {
		return getOperation(s) != null;
	}

	private final Operation operation;

	OperatorToken(String value, Operation operation) {
		super(value);
		this.operation = operation;
	}

	WhereItem applyOperation(WhereItem... values) {
		switch (operation) {
		case AND:
			return new WhereAndGroup((WhereExpression)values[0], (WhereExpression)values[1]);
		case OR:
			return new WhereOrGroup((WhereExpression)values[0], (WhereExpression)values[1]);
		case GREATER:{
			WhereGreaterGroup greaterGroup = new WhereGreaterGroup();
			for (WhereItem whereItem : values) {
				if(whereItem instanceof WhereField)
					greaterGroup.setField((WhereField) whereItem);
				else if (whereItem instanceof WhereValue)
					greaterGroup.setValue((WhereValue) whereItem);
			}
			return greaterGroup;
		}
		case SMALLER:
			WhereSmallerGroup smallerGroup = new WhereSmallerGroup();
			for (WhereItem whereItem : values) {
				if(whereItem instanceof WhereField)
					smallerGroup.setField((WhereField) whereItem);
				else if (whereItem instanceof WhereValue)
					smallerGroup.setValue((WhereValue) whereItem);
			}
			return smallerGroup;
		case EQUAL:
			WhereEqualGroup equalGroup = new WhereEqualGroup();
			for (WhereItem whereItem : values) {
				if(whereItem instanceof WhereField)
					equalGroup.setField((WhereField) whereItem);
				else if (whereItem instanceof WhereValue)
					equalGroup.setValue((WhereValue) whereItem);
			}
			return equalGroup;
		case SMALLER_EQUAL:
			WhereSmallerEqualGroup smallerEqualGroup = new WhereSmallerEqualGroup();
			for (WhereItem whereItem : values) {
				if(whereItem instanceof WhereField)
					smallerEqualGroup.setField((WhereField) whereItem);
				else if (whereItem instanceof WhereValue)
					smallerEqualGroup.setValue((WhereValue) whereItem);
			}
			return smallerEqualGroup;
		case GREATER_EQUAL:
			WhereGreateEqualGroup greaterEqualGroup = new WhereGreateEqualGroup();
			for (WhereItem whereItem : values) {
				if(whereItem instanceof WhereField)
					greaterEqualGroup.setField((WhereField) whereItem);
				else if (whereItem instanceof WhereValue)
					greaterEqualGroup.setValue((WhereValue) whereItem);
			}
			return greaterEqualGroup;
		case IN:
			WhereEqualGroup eqGroup = new WhereEqualGroup();
			for (WhereItem whereItem : values) {
				if(whereItem instanceof WhereField)
					eqGroup.setField((WhereField) whereItem);
				else if (whereItem instanceof WhereValue)
					eqGroup.setValue((WhereValue) whereItem);
			}
			return eqGroup;
		default:
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OperatorToken) {
			final OperatorToken t = (OperatorToken) obj;
			return t.getValue().equals(this.getValue());
		}
		return false;
	}

	int getOperandCount() {
		switch (operation) {
		case AND:
		case OR:
		case GREATER:
		case SMALLER:
		case EQUAL:
		case SMALLER_EQUAL:
		case GREATER_EQUAL:
		case IN:
			return 2;
		default:
			return 0;
		}
	}

	/**
	 * get the {@link Operation} of this {@link Token}
	 * 
	 * @return the {@link Operation}
	 */
	Operation getOperation() {
		return operation;
	}

	int getPrecedence() {
		return operation.precedence;
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	/**
	 * check if the operation is left associative
	 * 
	 * @return true if left associative, otherwise false
	 */
	boolean isLeftAssociative() {
		return operation.leftAssociative;
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		if (this.getOperandCount() == 2) {
			final WhereItem n2 = stack.pop();
			final WhereItem n1 = stack.pop();
			stack.push(this.applyOperation(n1, n2));
		} else if (this.getOperandCount() == 1) { //what it can return 1 ????
			final WhereItem n1 = stack.pop();
			stack.push(this.applyOperation(n1));
		}
	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack, List<Token> output) {
		Token before;
		while (!operatorStack.isEmpty() && (before = operatorStack.peek()) != null && (before instanceof OperatorToken)) {
				final OperatorToken stackOperator = (OperatorToken) before;
				if (this.isLeftAssociative() && this.getPrecedence() <= stackOperator.getPrecedence()) {
					output.add(operatorStack.pop());
				} else if (!this.isLeftAssociative() && this.getPrecedence() < stackOperator.getPrecedence()) {
					output.add(operatorStack.pop());
				} else {
					break;
				}
			}
		operatorStack.push(this);
	}
	
	@Override
	public String toString() {
		return String.format("OperatorToken [%s]", operation);
	}
}

