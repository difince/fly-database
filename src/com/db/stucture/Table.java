package com.db.stucture;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.db.utils.ServerSideError;

public class Table extends HasOffset{
	public String name;
	
	public Long nameOffset;
	
	public Long firstFieldOffset;
	
	public long firstRecordOffset;
	
	public Long nextOffset = new Long(0);

	public LinkedList<Field> fields = new LinkedList<>();
	
	@Override
	public String toString() {
		return String.format("name: %s, offset(Table): %d, nameOffset: %d, firstFieldOffset: %d, firstRecordOffset:%d, " +
				"nextOffset: %d , fields : %s", name,offset, nameOffset, firstFieldOffset, firstRecordOffset, nextOffset, fields);
	}
	
	public List<String> getAllFiledNames(){
		List<String> columnNames = new ArrayList<>();
		for (Field field : fields) {
			columnNames.add(field.name);
		}
		return columnNames;
	}
	
	public Field getFieldByName(String fieldName){
		for (Field field : fields) {
			if(field.name.equals(fieldName))
				return field;
		}
		throw new ServerSideError(String.format("Column with name '%s' does not exist.", fieldName));
	}

	public void validateFieldExistance(String fieldName) {
		for (Field field : fields) {
			if (field.name.equals(fieldName))
				return;
		}
		throw new ServerSideError(String.format("Column with name '%s' does not exist.", fieldName));
	}
}
