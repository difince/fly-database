package com.db.parser;

import java.util.regex.Pattern;

import com.db.statement.CreateTableStatement;
import com.db.statement.FieldValue;
import com.db.statement.SQLStatement;
import com.db.utils.ServerSideError;

public class CreateTableParser extends SQLParser {
	private static Pattern namePatern = Pattern.compile("[\\w]+");
	@Override
	public boolean accept(String sqlText) {
		if (sqlText.startsWith("create table"))
			return true;
		return false;
	}

	@Override
	public SQLStatement process(String sqlText) {
		CreateTableStatement statemnet = new CreateTableStatement();
		String[] queryParts = sqlText.split("\\(", 2);
		String tableName = (queryParts[0].split(" "))[2];
		if(!namePatern.matcher(tableName).matches())
			throw new ServerSideError(String.format("Error parsing table name: %s. Only letters, numbers and undescore are accepped.", tableName));
		statemnet.setTableName(tableName);
		if (sqlText.endsWith(")")) {
			String filedsPart = queryParts[1].substring(0, queryParts[1].length()-1);
			for (String sqlField : filedsPart.split(",")) {
				sqlField = sqlField.trim();
                FieldValue f = new FieldValue();
                String[] parts = sqlField.split(" ");
                String fieldName = parts[0];
                if(!namePatern.matcher(fieldName).matches())
        			throw new ServerSideError(String.format("Error parsing column name: %s. Only letters, numbers and undescore are accepped.", fieldName));
                f.setName(fieldName);
                if(parts[1].equals("int"))
                	f.setType(new Long(1));
                else if(parts[1].startsWith("varchar")){
                     f.setType(new Long(2));
					try {
						parts[1] = parts[1].replaceFirst("varchar\\(", "");
						String size = parts[1].substring(0,parts[1].length() - 1);
						f.setSize(Integer.parseInt(size));
					} catch (Exception e) {
						throw new ServerSideError("For varchar type size need to be specified -- example -- > varchar(100)");
					}
                }
                if(parts.length>2)
                	if(parts[2].equals("autoincrement"))
                		f.setAutoIncrement(true);
                statemnet.getFields().add(f);
			}
		}else{
			throw new RuntimeException("syntax error");
		}
		return statemnet;
	}

}
