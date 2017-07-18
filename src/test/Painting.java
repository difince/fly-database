package test;

import java.util.ArrayList;

public class Painting {
	private int id;
	private String name;
	private String technique;
	private int height;
	private int width;
	private int fraim;
	
	public Painting(String name, String technique, int height, int width, int fraim) {
		super();
		this.name = name;
		this.technique = technique;
		this.height = height;
		this.width = width;
		this.fraim = fraim;
	}
	public static ArrayList<Painting> paintings = new ArrayList<Painting>() {{
	    add(new Painting("Oткровеност","Масло", 20, 20, 1));
	    add(new Painting("Признание","Масло", 20, 20, 1));
	    add(new Painting("Деликатност","Масло", 40, 40, 1));
	    add(new Painting("Докосване","Масло", 27, 22, 1));
	    add(new Painting("Предизвикателство","Масло", 30, 30, 1));
	    add(new Painting("Преданост","Масло", 30, 30, 1));
	    add(new Painting("Чувство","Масло", 25, 25, 1));
	    add(new Painting("Усещане","Масло", 40, 45, 1));
	    add(new Painting("Докосване","Масло", 90, 90, 1));
	    add(new Painting("Обред","Масло", 54, 65, 1));
	    add(new Painting("Виталност","Масло", 80, 80, 1));
	    add(new Painting("Архаичност","Масло", 27, 36, 1));
	    add(new Painting("Интуиция","Масло", 75, 65, 1));
	    add(new Painting("Начало","Масло", 68, 72, 1));
	    add(new Painting("Страст","Масло", 80, 80, 1));
	    add(new Painting("Кръговрат","Масло", 100, 80, 1));
	    add(new Painting("Мираж","Масло", 88, 64, 1));
	    add(new Painting("Утеха","Масло", 92, 72, 1));
	    add(new Painting("Вълшебство","Масло", 100, 80, 1));
	    
	    add(new Painting("Вратата на слънцето","Графика", 15, 15, 1));
	    add(new Painting("Експресивност","Графика", 10, 10, 1));
	    add(new Painting("Настроение","Графика", 8, 6, 1));
	    add(new Painting("На дъното","Графика", 15, 15, 1));
	    add(new Painting("Страст","Графика", 80, 80, 1));
	    add(new Painting("Скален обект","Графика", 10, 10, 1));
	    add(new Painting("Форми","Графика", 10, 10, 1));
	    add(new Painting("Утеха","Графика", 92, 72, 1));
	    add(new Painting("Формообразуване","Графика", 19, 15, 1));
	}};
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
	public String getTechnique() {
		return technique;
	}
	public void setTechnique(String technique) {
		this.technique = technique;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getFraim() {
		return fraim;
	}
	public void setFraim(int fraim) {
		this.fraim = fraim;
	}
}
