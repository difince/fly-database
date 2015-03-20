package com.db.stucture;

public class Value {
    public Object value;
    public Long offset;
    public Field field;// zashto imam field ?
    public int idx = -1; // represent the place this value contain in record.values order
    
    @Override
    public String toString() {
    	return String.format("%s: %s; ",field.name,value);
    }
}
