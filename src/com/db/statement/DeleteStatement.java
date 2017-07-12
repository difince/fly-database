package com.db.statement;

import com.db.DBWriter;
import com.db.stucture.Cursor;
import com.db.stucture.Record;
import com.db.stucture.Table;

public class DeleteStatement extends WhereStatement{

	private String tableName;
	@Override
	public Object execute() {
		Table table = database.getTableByName(tableName);
		SelectStatement selectStatement = new SelectStatement();
		selectStatement.setTableName(tableName);
		selectStatement.database = database;
		selectStatement.writer = writer;
		selectStatement.reader = reader;
		if (getWhereExpression() == null) {
			table.firstRecordOffset = new Long(0);
			writer.updateFirstRecordOffset(table);
		} else {
			selectStatement.setWhereExpression(getWhereExpression());
			Cursor cursor = (Cursor) selectStatement.execute();
			while (cursor.hasNext()) {
				Record record = cursor.readRecord();
				if (record == null)
					continue;
				long previousRecordOffset = reader.getNextRecordOffset(table,record.offset);
				writer.updateNextRecordOffset(previousRecordOffset,record.nextRecordOffset);
				cursor.updateOffsets(previousRecordOffset,record.nextRecordOffset);
			}
		}
		return null;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
