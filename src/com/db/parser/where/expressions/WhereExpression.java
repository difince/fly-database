package com.db.parser.where.expressions;

import java.util.List;

import com.db.stucture.Value;

public interface WhereExpression extends WhereItem {
	public abstract boolean check(List<Value> values);
}
