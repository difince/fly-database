package com.db.stucture;

public class Field {
	public Long offset = new Long(0);

	public String name;
	public Long nameOffset = new Long(0);;
	
	public Long type;
	
	public int size;
	public Long nextFieldOffset = new Long(0);
	
	@Override
	public String toString() {
		return String.format("offset: %d, name :%s, nameOffset: %d, type: %d, size: %d, nextFieldOffset: %d", 
				offset, name, nameOffset, type, size , nextFieldOffset);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
