package com.db.statement;


public class ShowTableStatement extends SQLStatement{
	public static String COLUMN_NAME = "Column Names";
	@Override
	public Object execute() {
		return database.getAllTableNames();
	}

}
