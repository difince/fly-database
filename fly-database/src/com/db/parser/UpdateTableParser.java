package com.db.parser;

import com.db.parser.update.UpdateGroup;
import com.db.statement.SQLStatement;
import com.db.statement.UpdateStatement;

public class UpdateTableParser extends SQLParser {

	private WhereParser whereParser;
	@Override
	public boolean accept(String sqlText) {
		String[] txt = sqlText.split(" ");
		if(sqlText.startsWith("update") && txt.length > 3 && txt[2].equals("set"))
			return true;
		return false;
	}

	//update maintable set name = 'daiana' where id = 3
	@Override
	public SQLStatement process(String sqlText) {
		UpdateStatement stmt = new UpdateStatement();
		String[] sql = sqlText.split(" ");
		stmt.tableName = sql[1];
		String[] rslt = sqlText.split("set")[1].split("where");
	    for (String s : rslt[0].split(",")){
	    	UpdateGroup group = new UpdateGroup(s.split("=")[0].trim(), s.split("=")[1].trim());
	    	stmt.getUpdateGroups().add(group);
	    }
	    if(rslt.length>1){
	    	whereParser = new WhereParser(stmt);
	    	whereParser.process(rslt[1]);
	    }
		return stmt;
	}

}
