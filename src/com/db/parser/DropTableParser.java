package com.db.parser;

import com.db.statement.DropTableStatement;
import com.db.statement.SQLStatement;

public class DropTableParser extends SQLParser {

	@Override
	public boolean accept(String sqlText) {
		String[] arr = sqlText.split(" ");
		if (arr[0].equals("drop") && arr[1].equals("table") && arr.length > 1){
			return true;
		}
		return false;
	}

	@Override
	public SQLStatement process(String sqlText) {
		DropTableStatement stmt = new DropTableStatement();
		String[] arr = sqlText.split(" ");
		stmt.setTableName(arr[2]);
		return stmt;
	}

}
