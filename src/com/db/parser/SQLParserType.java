package com.db.parser;

public enum SQLParserType {
	CREATE_TABLE("create table"),
	DROP_TABLE("drop table"), 
	SELECT("select"), 
	INSERT("insert into"),
	DROP("drop"),
	DELETE("delete"),
	UPDATE("update");

	public String s;
	private SQLParserType(String s){
		this.s = s;
	}
	
	@Override
	public String toString() {
		return s;
	}
}
