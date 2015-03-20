package com.db;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import com.db.stucture.Database;
import com.db.stucture.Field;
import com.db.stucture.Table;

public class DBReader {
	public SeekableByteChannel channel;

	public Long readLong() {
		try {
			byte[] bytes = new byte[8];
			ByteBuffer buf = ByteBuffer.wrap(bytes);
			channel.read(buf);
			buf.position(0);
			return buf.getLong();
		} catch (IOException e) {
			return new Long(0);
		}
	}

	public Long readLong(Long position) {
		try {
			channel.position(position);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readLong();
	}

	public String readString(Long offset) {
		try {
			Long size = readLong(offset);
			byte[] bytes = new byte[size.intValue()];
			ByteBuffer buf = ByteBuffer.wrap(bytes);
			channel.read(buf);
			buf.position(0);
			return new String(buf.array()); 
		} catch (IOException e) {
			
			return "";
		}
	}
	
	public String readString() {
		try {
			Long size = readLong();
			byte[] bytes = new byte[size.intValue()];
			ByteBuffer buf = ByteBuffer.wrap(bytes);
			channel.read(buf);
			buf.position(0);
			return new String(buf.array()); 
		} catch (IOException e) {
			
			return "";
		}
	}

	public void readDatabase(Database database) {
		database.magic = readLong();
		database.version = readLong();
		database.firstTableOffset = readLong();
	}

	public Table readTable(Long tableOffset) {
		Table table = new Table();
		table.offset = tableOffset;
		readTable(table);
		return table;
	}

	public void readTable(Table table) {
		try {
			channel.position(table.offset);
			Long nameOffset = readLong();
			table.name = readString(nameOffset);
			table.firstFieldOffset = readLong(channel.position() +8);
			table.firstRecordOffset = readLong();
			table.nextOffset = readLong();
			table.fields.addAll(readFields(table.firstFieldOffset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Field> readFields(Long offset) {
		List<Field> fields = new ArrayList<>();
		while (offset != 0) {
			Field field = readField(offset);
			fields.add(field);
			offset = field.nextFieldOffset;
		}
		return fields;
	}
	
	private Field readField(Long offset) {
		Field field = new Field();
		try {
			field.offset = offset; //greshno
			field.nameOffset = readLong(new Long(offset));
			field.name = readString(field.nameOffset);
			field.type = readLong(new Long(channel.position() + 8));
			field.nextFieldOffset = readLong();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return field;
	}

	public long getLastRecordOffset(Table table) {
		long recordOffset = table.firstRecordOffset;
		if (recordOffset == 0){
			table.firstRecordOffset =  table.offset+16;
			return table.firstRecordOffset;
		}
		long nextRecorOffset = recordOffset;
		do {
			recordOffset = nextRecorOffset;
			nextRecorOffset = getNextRecordOffset(recordOffset);
		} while (nextRecorOffset != 0);
		return recordOffset;
	}

	
	public long getNextRecordOffset(Table table,Long offset) {
		long recordOffset = table.firstRecordOffset;
		if (recordOffset == 0){
			table.firstRecordOffset =  table.offset+16;
			return table.firstRecordOffset;
		}
		long nextRecorOffset = recordOffset;
		do {
			recordOffset = nextRecorOffset;
			nextRecorOffset = getNextRecordOffset(recordOffset);
		} while (nextRecorOffset != offset/* || nextRecorOffset != 0*/);
		return recordOffset;
	}
	
	private long getNextRecordOffset(long recordOffset) {
		try {
			channel.position(recordOffset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readLong();
	}
	
	public long getPreviosTableOffset(Table table, Long firstTableOffset) {
		long previousTableOffset = firstTableOffset;
		long nextTableOffset = previousTableOffset;
		do {
			previousTableOffset = nextTableOffset;
			nextTableOffset = getNextTableOffset(previousTableOffset);
		} while (nextTableOffset != table.nextOffset);
		return previousTableOffset;
	}

	private long getNextTableOffset(long tableOffset) {
		try {
			channel.position(tableOffset + 24);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readLong();
	}

//
//	public List<Record> readRecords(long recordOffset,List<Field> fileds) {
//		List<Record> records = new LinkedList<>();
//		while(recordOffset != 0){
//			Record record =  readRecord(recordOffset, fileds);
//			records.add(record);
//			recordOffset = record.nextRecordOffset;
//		}
//		return records;
//	}
//
//	public Record readRecord(long recordOffset, List<Field> fileds) {
//		Record record = new Record();
//		try {
//			record.offset = recordOffset;
//			record.nextRecordOffset = readLong(record.offset);
//			List<Value> values = new LinkedList<>();
//			for (Field field : fileds) {
//				Value val = new Value();
//				val.field = field;
//				if (field.type == 1) // int
//					val.value = readLong();
//				else if (field.type == 2)
//					val.value = readString(channel.position());
//				values.add(val);
//			}
//			record.values.addAll(values);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return record;
//	}
}
 