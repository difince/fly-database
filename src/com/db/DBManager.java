package com.db;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.db.parser.CreateTableParser;
import com.db.parser.DeleteParser;
import com.db.parser.DropTableParser;
import com.db.parser.InsertParser;
import com.db.parser.SQLParser;
import com.db.parser.SQLParserType;
import com.db.parser.SelectParser;
import com.db.parser.ShowTableParser;
import com.db.parser.UpdateTableParser;
import com.db.statement.SQLStatement;
import com.db.stucture.Database;
import com.db.stucture.Table;
import com.db.utils.Messages;
import com.db.utils.ServerSideError;

import test.Country;
import test.Student;
import test.Town;

public class DBManager {
	private ArrayList<SQLParser> parsers = new ArrayList<SQLParser>();
	public Database database = new Database();
	private DBReader reader = new DBReader();
	private DBWriter writer = new DBWriter();

	public static void main(String[] args) {
		DBManager manager = new DBManager();
		manager.connect("D:\\DIana\\DBData\\test.db");
		
		manager.executeQuery("create table student (id INT AUTOINCREMENT, first_name VARCHAR(50), second_name VARCHAR(50), last_name VARCHAR(50), fakulty_nember INT, EGN VARCHAR(100)");
		manager.executeQuery("create table country (id INT AUTOINCREMENT, name VARCHAR(50)");
		manager.executeQuery("create table town (id INT AUTOINCREMENT, name VARCHAR(50), country_id INT)");
		int i = 0;
		for(Student s : Student.students){
			manager.executeQuery(String.format("insert into student (id,first_name, second_name, last_name, fakulty_nember, EGN)"
					+ " values (%d,'%s','%s','%s','%d','%s')", i++, s.getFirstName(), s.getSecondName(), s.getLastName(), s.getFacultyNumber(), s.getEGN()));
		}
		
		i = 0;
		for (Country s : Country.country) {
			manager.executeQuery(String.format("insert into country (id,name)" + " values (%d,'%s')", i++, s.getName()));
		}
		
		i = 0;
		for (Town s : Town.towns) {
			manager.executeQuery(String.format("insert into town (id,name, country_id) values (%d,'%s', %d)", i++, s.getName(), s.getCountryId()));
		}
	}

	@SuppressWarnings("unchecked")
	public DBManager() {
		parsers.add(new CreateTableParser());
		parsers.add(new InsertParser());
		parsers.add(new SelectParser());
		parsers.add(new DropTableParser());
		parsers.add(new UpdateTableParser());
		parsers.add(new DeleteParser());
		parsers.add(new ShowTableParser());
	}

	public Object executeQuery(String query) {
		SQLStatement sqlStatement = null;
		try {
			for (SQLParser parser : parsers) {
				query = query.replaceAll("\\s+", " ").toLowerCase();
				if (parser.accept(query)) {
					sqlStatement = parser.process(query);
					sqlStatement.database = database;
					sqlStatement.writer = writer;
					sqlStatement.reader = reader;
					sqlStatement.execute();
					break;
				}
			}
		} catch (ServerSideError e) {
			throw e;
		} catch (Throwable e) {
			throw new RuntimeException(Messages.SYNTAX_ERR.getMessage() + e.getMessage(), e);
		}
		if(sqlStatement == null)
			throw new RuntimeException("Do not support such kind of query. Supportted are: " + Arrays.toString(SQLParserType.values()));
		return sqlStatement;
	}

	public Database connect(String path) {
		File file = new File(path);
		Boolean isNewDatabase = !file.exists();
		
		Set<OpenOption> options = new HashSet<OpenOption>();
		options.add(StandardOpenOption.READ);
		options.add(StandardOpenOption.WRITE);
		options.add(StandardOpenOption.CREATE);

		Set<PosixFilePermission> perms = PosixFilePermissions.fromString("---------");
		
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
		SeekableByteChannel sbc = null;
		try {
			Path p = Paths.get(path);
			sbc = Files.newByteChannel(p, options);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		writer.channel = sbc;
		reader.channel = sbc;
		
		if (isNewDatabase) {
			database.magic = new Long(7192);
			database.version = new Long(1);
			database.firstTableOffset = new Long(0);
			writer.writeDatabase(database);
		} else {
			reader.readDatabase(database);
			reader.readTable(database.firstTableOffset);
			Long offset = database.firstTableOffset;
			while (offset != 0) {
				Table table = reader.readTable(offset);
				database.addTabel(table);
				offset = table.nextOffset;
			}
		}
		return database;
	}
}
