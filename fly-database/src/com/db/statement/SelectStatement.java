package com.db.statement;

import java.util.LinkedList;

import com.db.stucture.Cursor;
import com.db.stucture.Field;
import com.db.stucture.Table;
import com.db.utils.ServerSideError;


public class SelectStatement extends WhereStatement{
	private String tableName;
	
	public Table table;
	public LinkedList<Field> fields = new LinkedList<Field>(); //columns
	public Cursor cursor = new Cursor();
	
	@Override
	public Object execute() {
		table =  database.getTableByName(tableName);
		if (fields.isEmpty()){//execute select * from ..
			fields.addAll(table.fields);
		}else {//execute select id , name from .. query
			for (Field userField : fields) {
				isFieldAccurate(userField);
			}
		}
	    cursor.statement = this;
		return cursor;
	}

	private void isFieldAccurate(Field userField) {
		for (Field tableField : table.fields) {
			if (userField.name.equals(tableField.name)){
				userField.type = tableField.type;
				return;
			}
		}
		throw new ServerSideError(String.format("%s does not have column with name ' %s ' . ", table.name,userField.name));
	}
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return String.format("tableName:%s, fields:%s", tableName,fields);
	}
}
