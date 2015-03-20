package com.db.statement;

import java.util.LinkedList;

import com.db.parser.where.ValueType;
import com.db.stucture.Field;
import com.db.stucture.Record;
import com.db.stucture.Table;
import com.db.stucture.Value;
import com.db.utils.ServerSideError;

public class InsertStatement extends SQLStatement{
	private String tableName;
	private LinkedList<FieldValue> fieldVals = new LinkedList<FieldValue>();

	@Override
	public Object execute() {
		Record record = new Record();
		Table table = database.getTableByName(tableName);
		for (FieldValue fieldValue : fieldVals) {
			Field field = getTableFieldByName(fieldValue.getName(),table);
			Value val = new Value();
			if (field.type == ValueType.DOUBLE.val) {
				val.value = new Long((String) fieldValue.getValue());
			} else {
				val.value = fieldValue.getValue();
			}
			val.field = field;
			record.values.add(val);
		}
		writer.writeRecord(record);
		writer.updateNextRecordOffset(reader.getLastRecordOffset(table), record.offset);
		return null;
//		Record record = new Record();
//		validateTableName(record);
//		for (Field field : record.table.fields) {
//			FieldValue fieldVal = getFieldByName(field.name);
//			Value val = new Value();
//			if (field.type == ValueType.STRING.val) {
//				val.value = new Long((String) fieldVal.getValue());
//			} else {
//				val.value = fieldVal.getValue();
//			}
//			val.field = field;
//			record.values.add(val);
//		}
//		writer.writeRecord(record);
//		writer.updateNextRecordOffset(reader.getLastRecordOffset(record.table), record.offset);
//		return null;
	}

	private Field getTableFieldByName(String name, Table table) {
		for (Field field : table.fields) {
			if(field.name.equals(name))
				return field;
		}
		throw new ServerSideError(String.format("Column with name  '%s' does not exists in table %s.", name,tableName));
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public LinkedList<FieldValue> getFields() {
		return fieldVals;
	}

	public void setFields(LinkedList<FieldValue> fields) {
		this.fieldVals = fields;
	}
	
	@Override
	public String toString() {
		return String.format("tableName: %s, fields:%s ", tableName,fieldVals);
	}
}
