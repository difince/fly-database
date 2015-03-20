package com.db.parser;

import com.db.statement.SQLStatement;
import com.db.statement.ShowTableStatement;

public class ShowTableParser extends SQLParser{

	@Override
	public boolean accept(String sqlText) {
		if(sqlText.equalsIgnoreCase("show tables"))
			return true;
		return false;
	}

	@Override
	public SQLStatement process(String sqlText) {
		return new ShowTableStatement();
	}

}
