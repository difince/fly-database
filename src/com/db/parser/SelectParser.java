package com.db.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.db.DBManager;
import com.db.statement.SQLStatement;
import com.db.statement.SelectStatement;
import com.db.stucture.Field;
import com.db.utils.ServerSideError;

public class SelectParser extends SQLParser {
	private WhereParser whereParser = null;
	final Pattern pattern = Pattern.compile("select (.+?) from ([\\w\\d]+)( where (.*))?");
	private DBManager dbManager;
	
	public SelectParser(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	@Override
	public boolean accept(String sqlText) {
		if (sqlText.trim().startsWith("select")) 
			return true;
		return false;
	}

	@Override
	public SQLStatement process(String sqlText) {
		SelectStatement statemnet = new SelectStatement();
		whereParser = new WhereParser(statemnet, dbManager);
		Matcher matcher = pattern.matcher(sqlText);
		
		if(!matcher.find())
			throw new ServerSideError("Syntax error");
		if(!matcher.group(1).equals("*")){
			List<Field> fields = new ArrayList<>();
			for (String f : matcher.group(1).split(",")) {
				Field fv = new Field();
				fv.name = f.trim();
				fields.add(fv);
			}
			statemnet.fields.addAll(fields);
		}
		statemnet.setTableName(matcher.group(2));
		if(matcher.group(4) != null)
			whereParser.process(matcher.group(4));
		return statemnet;
//		if(sqlText.startsWith("select * from")){
//			String subStr = sqlText.substring("select * from ".length()-1).trim();
//			statemnet.setTableName(subStr.split(" ")[0]);
//			if(whereParser.accept(sqlText)){
//				whereParser.process(subStr.split(" where ")[1]);
//			}
//		}else{
//			List<Field> fields = new ArrayList<>();
//			String[] data = sqlText.substring(6).split(" from ");
//			if (data.length != 2)
//				throw new ServerSideError("Syntax error");
//			String[] strFields = data[0].split(",");
//			for (String f : strFields) {
//				Field fv = new Field();
//				fv.name = f.trim();
//				fields.add(fv);
//			}
//			statemnet.getFields().addAll(fields);
//			statemnet.setTableName(data[1].trim().split(" ")[0]);
//		}
//		return statemnet;
	}
}

