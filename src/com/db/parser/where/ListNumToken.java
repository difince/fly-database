package com.db.parser.where;

import java.util.List;
import java.util.Stack;

import com.db.parser.where.expressions.WhereItem;

public class ListNumToken extends Token{

	ListNumToken(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mutateStackForCalculation(Stack<WhereItem> stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack, List<Token> output) {
		// TODO Auto-generated method stub
		
	}

}
