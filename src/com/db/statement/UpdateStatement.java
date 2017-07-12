package com.db.statement;

import java.util.ArrayList;
import java.util.List;

import com.db.parser.update.UpdateGroup;
import com.db.parser.where.ValueType;
import com.db.stucture.Cursor;
import com.db.stucture.Field;
import com.db.stucture.Record;
import com.db.stucture.Table;
import com.db.stucture.Value;

public class UpdateStatement extends WhereStatement {

	public String tableName;
	private List<UpdateGroup> updateGroups = new ArrayList<>();

	//update maintable set name = 'daiana' where id = 4
	//update dianatable set fakultetennomer = 'daiana' where id = 4
	
	@Override
	public Object execute() {
		Table table = database.getTableByName(tableName);
		for (UpdateGroup group : updateGroups) {
			Field field = table.getFieldByName(group.getFieldName());	
			validateCompatibility(field.type, group.getValue());
		}
		
		SelectStatement selectStatement = new SelectStatement();
		selectStatement.setTableName(tableName);
		selectStatement.database = database;
		selectStatement.writer = writer;
		selectStatement.reader = reader;
		selectStatement.setWhereExpression(getWhereExpression());
		Cursor cursor = (Cursor) selectStatement.execute();
		while (cursor.hasNext()) {
			Record record = cursor.readRecord();
			if(record == null)
				continue;
			for (Value v : record.values) {
				for (UpdateGroup group : updateGroups) {
					if(v.field.name.equals(group.getFieldName())){
						v.value = group.getValue();
					}
				}
			}
			Long oldCurrOffset = record.offset;
			writer.writeRecord(record);
			cursor.updateOffsets(record.offset, record.nextRecordOffset);
			writer.updateNextRecordOffset(reader.getNextRecordOffset(table, oldCurrOffset), record.offset);
		}
		return null;
	}

	private void validateCompatibility(Long type, String value) {
		try {
			if (ValueType.valueOf(type) == ValueType.DOUBLE) {
				Double.parseDouble(value);
			}
		} catch (NumberFormatException e) {
			throw new RuntimeException(String.format("Incompatible type of value:%s. Expected type to be :%s.", value,ValueType.toString(type)),e);
		}
	}

	public List<UpdateGroup> getUpdateGroups() {
		return updateGroups;
	}

	public void setUpdateGroups(List<UpdateGroup> updateGroups) {
		this.updateGroups = updateGroups;
	}

}
