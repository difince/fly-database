package com.db.stucture;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import com.db.utils.ServerSideError;

public class Database extends Observable {
	public Long magic;
    public Long version;
    public Long firstTableOffset;
    public LinkedList<Table> tables = new LinkedList<>();
    
    public List<String> getAllTableNames(){
    	List<String> names = new ArrayList<>();
    	for (Table table : tables) {
    		names.add(table.name);
    	}
    	return names;
    }
    
    public Table getTableByName(String name){
    	for (Table table : tables) {
			if(table.name.equalsIgnoreCase(name)){
				return table;
			}
		}
    	throw new ServerSideError("Table with name " + name+ " does not exists.");
    }
    
    public boolean isTableExists(String name){
    	for (Table table : tables) {
			if(table.name.equalsIgnoreCase(name)){
				return true;
			}
		}
    	return false;
    }
    
    @Override
    public String toString() {
    	return String.format("magic: %d, version: %d, firstTableOffset : %d, tables: %s", magic ,version, firstTableOffset , tables);
    }
    
    public void addTabel(Table table){
    	tables.add(table);
    	setChanged();
    	notifyObservers(table);
    	
    }
    
    public void removeTable (Table table){
    	tables.remove(table);
    	setChanged();
    	notifyObservers(table);
    }
}
