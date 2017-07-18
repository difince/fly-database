package test;

import java.util.ArrayList;

public class Town {
	private int id;
	private String name;
	private Integer countryId;

	public Town(String name, Integer countryId) {
		super();
		this.name = name;
		this.countryId = countryId;
	}

	public static ArrayList<Town> towns = new ArrayList<Town>() {
		{
			add(new Town("Sofia", 1));
			add(new Town("Plovdiv", 1));
			add(new Town("Varna", 1));
			add(new Town("Yambol", 1));
			add(new Town("Kazanlak", 1));
			add(new Town("Ruse", 1));
			add(new Town("Stara Zagora", 1));
			add(new Town("Burgas", 1));
			add(new Town("Hambourg", 2));
			add(new Town("Athina", 3));
			add(new Town("Paris", 4));
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Town [id=" + id + ", name=" + name + ", countryId=" + countryId + "]";
	}
}

