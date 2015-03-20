package com.db.stucture;

import java.util.LinkedList;
import java.util.List;

import com.db.parser.where.ValueType;
import com.db.statement.SelectStatement;

public class Cursor {
	public SelectStatement statement;
	public Long currRecordOffset = new Long(0);
	public Long currOffset = new Long(0);
	public Long nextRecordOffset = new Long(0);
//	private boolean isLast = false;

	public boolean next() {
		if (currRecordOffset == 0) {
			if (statement.table.firstRecordOffset != 0) {
				currRecordOffset = statement.reader.readLong(statement.table.firstRecordOffset);
				currOffset= currRecordOffset;
				nextRecordOffset = statement.reader.readLong(currRecordOffset);
				return true;
			}
			return false;
		} else {
			nextRecordOffset = statement.reader.readLong(currRecordOffset);
			if (nextRecordOffset != 0) {
				currRecordOffset = nextRecordOffset;
				currOffset = currRecordOffset;
				nextRecordOffset = statement.reader.readLong(currRecordOffset);
				return true;
			} else {
//				if(!isLast){
//					isLast = true;
//					return true;
//				}
				return false;
			}
		}
	}
	
	public void updateOffsets(Long currOffset, Long nextOffset){
        this.currRecordOffset = currOffset;
        this.nextRecordOffset = nextOffset;
	}
	
	public Record readRecord(){
		Record r= new Record();
		for (Field field : statement.table.fields) {
			Value val = new Value();
			if(field.type == ValueType.DOUBLE.val)
				val.value = statement.reader.readLong();
			else if(field.type == ValueType.STRING.val)
				val.value = statement.reader.readString();
			val.field = field;
			r.values.add(val);
			r.offset = currOffset;
			r.nextRecordOffset = nextRecordOffset;
		}
		if(statement.getWhereExpression() == null || statement.getWhereExpression().check(r.values)){
			return r;
		}
		return null;
	}
	
	public Object[] read(){
		Object[] rslt = new Object[statement.fields.size()];
		List<Value> values = new LinkedList<>();
		for (Field field : statement.table.fields) {
			Value val = new Value();
			if(field.type == ValueType.DOUBLE.val)
				val.value = statement.reader.readLong();
			else if(field.type == ValueType.STRING.val)
				val.value = statement.reader.readString();
			
			val.field = field;
			if(statement.fields.indexOf(field) != -1){
			   val.idx = statement.fields.indexOf(field);
			}
			values.add(val);
		}
		if(statement.getWhereExpression() == null || statement.getWhereExpression().check(values)){
			for (Value v : values) {
				if (v.idx != -1)
					rslt[v.idx] = v.value;
			}
		}
		return rslt;
	}

	public String[] getFieldNames(){
		String[] fieldsName = new String[statement.fields.size()];
		for (int i = 0; i < statement.fields.size(); i++) {
			fieldsName[i] = statement.fields.get(i).name;
		}
		return fieldsName;
	}
}
