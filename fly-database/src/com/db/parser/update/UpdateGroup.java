package com.db.parser.update;


public class UpdateGroup {

	public UpdateGroup(String fieldName, String value) {
		this.fieldName = fieldName;
		this.value = value;
	}

	private String fieldName;
	private String value;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UpdateGroup [fieldName=" + fieldName + ", value=" + value + "]";
	}
}
