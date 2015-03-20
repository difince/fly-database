package com.db.utils;

public enum Messages {
	QUERY_EMPTY("No query entered"),
	QUERY_SUCCESSFULLY_EXECUTED("Query executed successfully."),
	SYNTAX_ERR("Syntax error.Check query and try again.");
	public String message;

	private Messages(String message) {
		this.message = message;
	}

	public String getMessage(String... params) {
		String message = new String(this.message);
		for (String param : params) {
			int i = param.indexOf('=');
			String p = "{" + param.substring(0, i) + "}";
			String v = param.substring(i + 1);
			message = message.replace(p, v);
		}
		return message;
	}
}
