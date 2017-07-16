package com.db.statement;

import java.util.ArrayList;
import java.util.List;

public class ListStatement extends SQLStatement{
	private List<Object> objectList = new ArrayList<>();
	public ListStatement(List<Object> res) {
		this.objectList = res;
	}

	@Override
	public Object execute() {
		return objectList;
	}

	public List<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

}
