package com.db.parser.where;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.db.DBManager;
import com.db.parser.where.expressions.WhereItem;
import com.db.parser.where.expressions.WhereValue;
import com.db.statement.ListStatement;
import com.db.statement.SQLStatement;
import com.db.statement.SelectStatement;
import com.db.stucture.Cursor;

public class SubQueryToken extends Token{
	private DBManager dbManager;

	SubQueryToken(String value,DBManager dbManager) {
		super(value);
		this.dbManager = dbManager;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack, List<Token> output) {
		output.add(this);
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		List<Object> r =  new ArrayList<>();
		SQLStatement sqlStatement = (SQLStatement)dbManager.executeQuery(getValue());
		if(sqlStatement instanceof SelectStatement){
			Cursor cursor = ((SelectStatement)sqlStatement).cursor;
			while (cursor.hasNext()) {
				Object[] rslt = cursor.next();
				if (!isEmptyResult(rslt))
					r.add(rslt);
			}
		} else if (sqlStatement instanceof ListStatement) {
			r = ((ListStatement)sqlStatement).getObjectList();
		}else {
			new RuntimeException("Wrong sub-query type.");
		}
		WhereValue val = new WhereValue(ValueType.LIST_NUM, r);
		stack.push(val);
	}

	private boolean isEmptyResult(Object[] rslt) {
		for (Object object : rslt) {
			if(object != null)
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("SubQueryToken [%s]", super.toString());
	}
}
