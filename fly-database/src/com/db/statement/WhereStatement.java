package com.db.statement;

import com.db.parser.where.expressions.WhereExpression;

public abstract class WhereStatement extends SQLStatement{
	private WhereExpression whereExpression;

	public WhereExpression getWhereExpression() {
		return whereExpression;
	}

	public void setWhereExpression(WhereExpression whereExpression) {
		this.whereExpression = whereExpression;
	}
}
