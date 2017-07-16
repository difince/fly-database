package com.db.parser;

import com.db.DBManager;
import com.db.statement.DeleteStatement;
import com.db.statement.SQLStatement;

public class DeleteParser extends SQLParser {

	private WhereParser whereParser;
	private DBManager dbManager;
	
	public DeleteParser(DBManager dbManager) {
		this.dbManager = dbManager;
	}
	
	@Override
	public boolean accept(String sqlText) {
		String[] arr = sqlText.split(" ");
		if (arr[0].equals("delete") && arr[1].equals("from") && arr.length > 1){
			return true;
		}
		return false;
	}

	@Override
	public SQLStatement process(String sqlText) {
		DeleteStatement stmt = new DeleteStatement();
		String[] rslt = sqlText.split(" ");
		stmt.setTableName(rslt[2]);
		String[] whereRslt = sqlText.split("where");
		if(whereRslt.length > 1){
			whereParser = new WhereParser(stmt, dbManager);
	    	whereParser.process(whereRslt[1]);
		}
		return stmt;
	}

}
