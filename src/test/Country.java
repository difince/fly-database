package test;

import java.util.ArrayList;

public class Country {
	private int id;
	private String name;

	public Country(String name) {
		super();
		this.name = name;
	}

	public static ArrayList<Country> country = new ArrayList<Country>() {
		{
			add(new Country("Bulgaria"));
			add(new Country("Gernamy"));
			add(new Country("Greece"));
			add(new Country("France"));
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
