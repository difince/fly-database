package com.db.parser.where.expressions.booleans;

import java.util.List;

import com.db.parser.where.expressions.WhereExpression;
import com.db.stucture.Value;



public class WhereAndGroup extends BooleanExpression{

	public WhereAndGroup(WhereExpression expr1, WhereExpression expr2) {
		super(expr1, expr2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean check(List<Value> values) {
		if(getExpr1().check(values) && getExpr2().check(values))
			return true;
		return false;
	}

}
