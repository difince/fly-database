package com.db.statement;

import com.db.stucture.Table;

public class DropTableStatement  extends SQLStatement{
	private String tableName;
	
	@Override
	public Object execute() {
		Table table = database.getTableByName(tableName);
		Table previosTable = null;
		for (Table t :database.tables){
			if(t.nextOffset == table.offset){
				previosTable = t;
				break;
			}
		}
		previosTable.nextOffset = table.nextOffset;
		writer.updateNextTableOffset(previosTable);
		database.removeTable(table);
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "DropTableStatement [tableName=" + tableName + "]";
	}
	

}
