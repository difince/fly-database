package com.db.parser;

import com.db.statement.SQLStatement;

public abstract class SQLParser {
    public abstract boolean accept(String sqlText);
    public abstract SQLStatement process(String sqlText);
}
