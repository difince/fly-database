package com.db.stucture;

import java.util.LinkedList;
import java.util.List;

public class Record extends HasOffset{
	public Long nextRecordOffset = new Long(0);
	public List<Value> values = new LinkedList<>();
	@Override
	public String toString() {
		return "Record [nextRecordOffset=" + nextRecordOffset + ", values="
				+ values + ", offset=" + offset + "]";
	}
}
