package com.db.parser.where.expressions;

import com.db.parser.where.ValueType;

public class WhereValue implements WhereItem{

	private ValueType type;
	private Object value;
	
	public WhereValue(ValueType type, Object value){
		this.type = type;
		this.value = value;
	}

	public ValueType getType() {
		return type;
	}

	public void setType(ValueType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("WhereValue [type : %s, value: %s]", type, value);
	}
	
} 
