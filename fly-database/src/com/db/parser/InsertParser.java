package com.db.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.db.statement.FieldValue;
import com.db.statement.InsertStatement;
import com.db.statement.SQLStatement;
import com.db.utils.ServerSideError;

public class InsertParser extends SQLParser {
	private static final Pattern pattern = Pattern.compile("\\((.*?)\\)",Pattern.DOTALL);

	@Override
	public boolean accept(String sqlText) {
		if (sqlText.startsWith("insert into"))
			return true;
		return false;
	}

	@Override
	public SQLStatement process(String sqlText) {
		InsertStatement statemnet = new InsertStatement();
		String[] splitsqlText = sqlText.split(" ");
		statemnet.setTableName(splitsqlText[2]);
		parseFieldPairs(statemnet, sqlText);
		return statemnet;
	}

	private void parseFieldPairs(InsertStatement statemnet, String sqlText) {
		Matcher matcher = pattern.matcher(sqlText);
		ArrayList<String> nameValue = new ArrayList<String>();
		while (matcher.find()) {
			nameValue.add(matcher.group(1));
		}
		String[] names = trim(nameValue.get(0).split(","));
		String[] values = trim(nameValue.get(1).split(","));
		convertValues(values);
		for (int i = 0; i < names.length; i++) {
			FieldValue f = new FieldValue();
			for (int j = 0; j < values.length; j++) {
				if (i == j) {
					f.setName(names[i]);
					f.setValue(values[j]);
				}
			}
			statemnet.getFields().add(f);
		}
	}

	private String[] trim(String[] arr) {
		String[] trimedArr = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			trimedArr[i] = arr[i].trim();
		}
		return trimedArr;
	}

	private void convertValues(String[] values) {
		for  (int i = 0; i < values.length; i++)  {
			if(isStringValue(values[i])){
				values[i] = values[i].substring(1, values[i].length()-1);
				continue;
			}else if (!isNumericValue(values[i])) {
				throw new ServerSideError(String.format("Error parsing '%s'", values[i]));
			}
		}
	}

//	private boolean isNumericValue(String value) {
//		if((value.startsWith("'") && !value.endsWith("'")) || (!value.startsWith("'") && value.endsWith("'")))
//			return false;
//		return true;
//	}
	
	private static boolean isNumericValue(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private boolean isStringValue(String value) {
		if(value.startsWith("'") && value.endsWith("'"))
			return true;
		return false;
	}
}
