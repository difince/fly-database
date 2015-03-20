package com.db.parser.where.expressions.comparison;

import com.db.parser.where.expressions.WhereExpression;
import com.db.parser.where.expressions.WhereField;
import com.db.parser.where.expressions.WhereValue;

public abstract class ComparisonExpression implements WhereExpression {
	private WhereField field;
	private WhereValue value;

	public ComparisonExpression(){}
	
	public ComparisonExpression(WhereField field, WhereValue val) {
		this.field = field;
		this.value = val;
	}

	public WhereField getField() {
		return field;
	}

	public void setField(WhereField field) {
		this.field = field;
	}

	public WhereValue getValue() {
		return value;
	}

	public void setValue(WhereValue value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%s,%s]; ", field, value);
	}
}
