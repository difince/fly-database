package com.db.utils;

import java.util.ArrayList;
import java.util.List;

public class Spliter {
	private static List<String> specialChrs = new ArrayList<String>() {{
    add("*");
    add("/");
    add("+");
    add("-");
    add("=");
    add(">");
    add("<");
    add("(");
    add(")");
}};
	
	public static List<String> execute(String str) {
		List<String> s = new ArrayList<>();
		StringBuffer buf = new StringBuffer();
		for (char c : str.toCharArray()) {
			if(Character.isWhitespace(c)){
				if (!buf.toString().equals("")) {
					s.add(buf.toString().trim());
					buf = new StringBuffer();
				}
			}else if (specialChrs.contains(Character.toString(c))) {
				if (!buf.toString().equals("")) {
					s.add(buf.toString());
					buf = new StringBuffer();
				}
				s.add(Character.toString(c));
			} else {
				buf.append(c);
			}
		}
		return s;
	}
}
