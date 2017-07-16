package com.db.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.db.statement.ListStatement;
import com.db.statement.SQLStatement;

public class ListParser extends SQLParser{
	final static Pattern numberPattern = Pattern.compile("^([\\d]+)$");
	final static Pattern wordPattern = Pattern.compile("^('[\\w\\s]+)");
	// 'bulgaria', 'zaza'
	// 1,3,5,78

	@Override
	public boolean accept(String sqlText) {
		String[] tokens = sqlText.split(",");
		for (String token : tokens) {
			Matcher number = numberPattern.matcher(token);
			Matcher word = wordPattern.matcher(token);
			if(false/*!number.find() && !word.find()*/)
				throw new RuntimeException("Cannot parse sub-wuery.");
		}
		return true;
	}

	@Override
	public SQLStatement process(String sqlText) {
		String[] tokens = sqlText.split(",");
		List<Object> res = new ArrayList<Object>();
		for (String token : tokens) {
			token = token.replace("'", "").trim();
			res.add(token);
		}
		return new ListStatement(res);
	}
	public static void main(String[] args) {
		String s = "'bulgaria'";
		Matcher word = wordPattern.matcher(s);
		System.err.println(word.find());
	}
}
