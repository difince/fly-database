package com.db.statement;

import java.util.LinkedList;

import com.db.stucture.Table;
import com.db.utils.ServerSideError;

public class CreateTableStatement extends SQLStatement {
	private String tableName;
	private LinkedList<FieldValue> fields = new LinkedList<FieldValue>();
	
	@Override
	public Object execute() {
	
		Table table = new Table();
		table.name = getTableName();
		table.firstFieldOffset = new Long(0); //pyrvona4alno 0
		table.firstRecordOffset = new Long(0);

		if(database.isTableExists(tableName))
			throw new ServerSideError(String.format("Table with name %s already exists.", tableName));
		
		writer.writeNewTable(table);
		if (database.firstTableOffset == 0) {			
			database.firstTableOffset = table.offset;
			writer.writeDatabase(database);
		}
		else {
			Table previousTable = database.tables.get(database.tables.size()-1);
			previousTable.nextOffset = table.offset;
			writer.updateNextTableOffset(previousTable);
		}
		
		for (FieldValue field : fields) {
			com.db.stucture.Field newField = new com.db.stucture.Field();
			newField.name = field.getName();
			newField.type = field.getType();
			newField.size = field.getSize();
			
			writer.writeField(newField);
			table.fields.add(newField);
		}
		
		com.db.stucture.Field[] arr = table.fields.toArray(new com.db.stucture.Field[table.fields.size()]);
		for (int i = 0; i < arr.length; i++) {
			if(i == 0){
				table.firstFieldOffset = arr[0].offset;
				writer.updateFirstFieldOffset(table);
			}else{
				arr[i-1].nextFieldOffset =arr[i].offset;
				writer.updateNextFieldOffset(arr[i-1]);
			}
		}
		database.addTabel(table);
		return null;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public LinkedList<FieldValue> getFields() {
		return fields;
	}

	public void setFields(LinkedList<FieldValue> fields) {
		this.fields = fields;
	}
	
	@Override
	public String toString() {
		return String.format("tableName: %s, fields:%s ", tableName,fields);
	}
}
