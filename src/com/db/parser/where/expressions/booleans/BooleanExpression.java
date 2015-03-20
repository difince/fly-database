package com.db.parser.where.expressions.booleans;

import com.db.parser.where.expressions.WhereExpression;

public abstract class BooleanExpression implements WhereExpression {

	private WhereExpression expr1;
	private WhereExpression expr2;

	public BooleanExpression(WhereExpression expr1,WhereExpression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	public WhereExpression getExpr1() {
		return expr1;
	}

	public void setExpr1(WhereExpression expr1) {
		this.expr1 = expr1;
	}

	public WhereExpression getExpr2() {
		return expr2;
	}

	public void setExpr2(WhereExpression expr2) {
		this.expr2 = expr2;
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s", expr1,expr2);
	}
}
