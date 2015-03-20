package com.db.parser.where.expressions;

public class WhereField implements WhereItem{
    private String fieldName;
    
    public WhereField(String fieldName){
    	this.fieldName = fieldName;
    }

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("WhereField [fieldName: %s] ", fieldName);
	}
}
