package com.db;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

import com.db.parser.where.ValueType;
import com.db.stucture.Database;
import com.db.stucture.Record;
import com.db.stucture.Table;
import com.db.stucture.Value;

public class DBWriter {
	public SeekableByteChannel channel;

	public void writeDatabase(Database database) {
		try {
			channel.position(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeLong(database.magic);
		writeLong(database.version);
		writeLong(database.firstTableOffset);
	}

	public Long writeLong(Long value) {
		Long valOffset = new Long(0);
		try {
			valOffset = channel.position();
			ByteBuffer buf = ByteBuffer.allocate(8).putLong(value);
			buf.position(0);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valOffset;
	}

	public Long writeString(String value) {
		Long stringOffset = new Long(0);
		try {
			stringOffset = channel.position();
			byte[] bytes = value.getBytes();
			writeLong(new Long(bytes.length));
			ByteBuffer buf = ByteBuffer.wrap(bytes);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringOffset;
	}

	public void writeNewTable(Table table) {
		try {
			setChannelEndPossition();
			table.nameOffset = writeString(table.name);
			table.offset = channel.position();
			writeLong(table.nameOffset);
			writeLong(table.firstFieldOffset);
			writeLong(table.firstRecordOffset);
			writeLong(table.nextOffset);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeField(com.db.stucture.Field field) {
		try {
			setChannelEndPossition();
			field.nameOffset = writeString(field.name);
			field.offset = channel.position();
			writeLong(field.nameOffset);
			writeLong(new Long(field.type));
			writeLong(new Long(field.nextFieldOffset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateFirstTableOffset(Long offset){
		
	}
	
	public void updateFirstFieldOffset(Table table) {
		try {
			channel.position(table.offset + 8);
			ByteBuffer buf = ByteBuffer.allocate(8).putLong(table.firstFieldOffset);
			buf.position(0);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateFirstRecordOffset(Table table){
		try {
			channel.position(table.offset + 16);
			ByteBuffer buf = ByteBuffer.allocate(8).putLong(table.firstRecordOffset);
			buf.position(0);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateNextTableOffset(Table table) {
		try {
			channel.position(table.offset + 24);
			ByteBuffer buf = ByteBuffer.allocate(8).putLong(table.nextOffset);
			buf.position(0);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateNextTableOffset(Long tableOffset, Long nextTableOffset) {
		try {
			channel.position(tableOffset + 24);
			ByteBuffer buf = ByteBuffer.allocate(8).putLong(nextTableOffset);
			buf.position(0);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateNextFieldOffset(com.db.stucture.Field field) {
		try {
			channel.position(field.offset + 16);
			ByteBuffer buf = ByteBuffer.allocate(8).putLong(field.nextFieldOffset);
			buf.position(0);
			channel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateNextRecordOffset(long recordOffset, long nextRecordOffset){
		try {
			channel.position(recordOffset);
			writeLong(nextRecordOffset);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setChannelEndPossition(){
		try {
			channel.position(new Long(channel.size()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeRecord(Record record) {
		try {
			setChannelEndPossition();
			record.offset = channel.position();
			if(record.nextRecordOffset != 0){
				writeLong(record.nextRecordOffset);
			} else {
				writeLong(new Long(0));
			}
			for (Value value : record.values) {
				if (value.field.type == ValueType.DOUBLE.val) { // 1 == INT
					writeLong((Long) value.value);
				}else if (value.field.type ==2){
					writeString((String)value.value);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
