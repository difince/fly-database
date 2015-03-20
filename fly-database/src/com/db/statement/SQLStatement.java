package com.db.statement;

import com.db.DBReader;
import com.db.DBWriter;
import com.db.stucture.Database;

public abstract class SQLStatement {
	
	public DBWriter writer;
	public DBReader reader;
	public Database database;

	public abstract Object execute();
}
