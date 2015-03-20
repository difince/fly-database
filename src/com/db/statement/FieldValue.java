package com.db.statement;

public class FieldValue {
	private String name;
	private Object value;
	private Long type;
	private int size;
	private boolean autoIncrement = false;

	//dbstructure
	private Long nextFieldOffset;
	private Long offset;
	private Long nameOffset;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format("name:%s, value:%s, type: %d, size:%d, autoIncremented:%s; ", name,value,type,size, autoIncrement);
				
	}

	public Long getNextFieldOffset() {
		return nextFieldOffset;
	}

	public void setNextFieldOffset(Long nextFieldOffset) {
		this.nextFieldOffset = nextFieldOffset;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getNameOffset() {
		return nameOffset;
	}

	public void setNameOffset(Long nameOffset) {
		this.nameOffset = nameOffset;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
