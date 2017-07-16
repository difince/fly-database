package com.db.parser;

import java.util.ArrayList;
import java.util.Stack;

import com.db.DBManager;
import com.db.parser.where.Token;
import com.db.parser.where.Tokenizer;
import com.db.parser.where.expressions.WhereExpression;
import com.db.parser.where.expressions.WhereItem;
import com.db.statement.SQLStatement;
import com.db.statement.WhereStatement;

public class WhereParser extends SQLParser{
	private WhereStatement stmt;
	private DBManager dbManager;

	public WhereParser(WhereStatement stmt, DBManager dbManager) {
		this.stmt = stmt;
		this.dbManager = dbManager;
	}

	@Override
	public SQLStatement process(String query) {
		Token[] tokens = new Tokenizer().tokenize(query, dbManager);
		
		final ArrayList<Token> outputTokens = new ArrayList<>();
		final Stack<Token> operatorStack = new Stack<Token>();
		for (final Token token : tokens) {
			token.mutateStackForInfixTranslation(operatorStack, outputTokens);
		}
		// all tokens read, put the rest of the operations on the output;
		while (operatorStack.size() > 0) {
			outputTokens.add(operatorStack.pop());
		}
		stmt.setWhereExpression(transformToWhereItem(outputTokens.toArray(new Token[outputTokens.size()])));
		return stmt;
	}

	private WhereExpression transformToWhereItem(Token[] tokens) {
		final Stack<WhereItem> stack = new Stack<WhereItem>();
		for (final Token t : tokens) {
			((Token) t).mutateStackForCalculation(stack);
		}
		for (WhereItem whereItem : stack) {
			if (!WhereExpression.class.isAssignableFrom(whereItem.getClass()))
				throw new RuntimeException();
		}
		WhereExpression obj = (WhereExpression)stack.pop();
		return obj;
	}

	@Override
	public boolean accept(String sqlText) {
//		if(sqlText.contains(" where ") && sqlText.split(" where ").length == 2)
//			return true;
		return false;
	}

	public SQLStatement getStmt() {
		return stmt;
	}
	public void setStmt(WhereStatement stmt) {
		this.stmt = stmt;
	}
}
