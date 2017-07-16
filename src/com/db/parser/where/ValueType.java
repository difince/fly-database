package com.db.parser.where;


public enum ValueType {
	DOUBLE(1), STRING(2), EXPRESSION(3), LIST_NUM(4);

	@SuppressWarnings("unused")
	public int val;

	private ValueType(int val) {
		this.val = val;
	}

	public static ValueType valueOf(Long type) {
		if(type == 1){
			return ValueType.DOUBLE;
		}else if (type == 2){
			return ValueType.STRING;
		} else if(type == 3){
			return ValueType.EXPRESSION;
		}else if(type == 4){
			return ValueType.LIST_NUM;
		}
		throw new RuntimeException("Unknown ValueType: " + type);
	}
	
	public static String toString(Long type){
		if(type == 1){
			return "DOUBLE";
		}else if (type == 2){
			return "STRING";
		} else if(type == 3){
			return "EXPRESSION";
		}else if(type == 3){
			return "LIST_NUM";
		}
		throw new RuntimeException("Unknown ValueType: " + type);
	}
}
