package test;

import java.util.ArrayList;

public class Painting {
	private int id;
	private String name;
	private String technique;
	private int height;
	private int width;
	private int fraim;
	private int price;
	private String state;
	private int gallery_id;
	
	public Painting(String name, String technique, int height, int width, int fraim, int price, String state, int gallery_id) {
		super();
		this.name = name;
		this.technique = technique;
		this.height = height;
		this.width = width;
		this.fraim = fraim;
		this.price = price;
		this.state = state;
		this.gallery_id = gallery_id;
	}
	public static ArrayList<Painting> paintings = new ArrayList<Painting>() {{
		add(new Painting("Oткровеност","Масло", 20, 20, 1,165, "Available",0));
		add(new Painting("Деликатност","Масло", 40, 40, 1, 170, "Available",0));
		add(new Painting("Докосване","Масло", 27, 22, 1, 165, "Available", 0));
		add(new Painting("Вратата на слънцето","Графика", 15, 15, 1, 60, "Available", 0));
		add(new Painting("На дъното","Графика", 15, 15, 1, 100, "Available", 0));
		add(new Painting("Утеха","Масло", 92, 72, 1, 95, "Available", 0));
	    add(new Painting("Признание","Масло", 20, 20, 1, 145, "Sold",0));
	    add(new Painting("Предизвикателство","Масло", 30, 130, 1, 65, "Sold", 0));
	    add(new Painting("Преданост","Масло", 30, 30, 1, 145, "Sold", 0));
	    add(new Painting("Обред","Масло", 54, 65, 1, 130, "Sold", 0));
	    add(new Painting("Начало","Масло", 68, 72, 1, 65, "Sold", 0));
	    add(new Painting("Мираж","Масло", 88, 64, 1, 190, "Sold", 0));
	    add(new Painting("Виталност","Масло", 80, 80, 1, 600, "Sold", 0));
	    add(new Painting("Вълшебство","Масло", 100, 80, 1, 60, "Sold",0));
	    add(new Painting("Експресивност","Графика", 10, 10, 1, 50, "Sold", 0));
	    add(new Painting("Настроение","Графика", 8, 6, 1, 80, "Sold", 0));
	    add(new Painting("Страст","Графика", 80, 80, 1, 90, "Sold", 0));
	    
	    
	    add(new Painting("Чувство","Масло", 25, 25, 1, 105, "inStore", 5));
	    add(new Painting("Усещане","Масло", 40, 45, 1, 80, "inStore", 6));
	    add(new Painting("Докосване","Масло", 90, 90, 1, 120, "inStore", 7));
	    add(new Painting("Архаичност","Масло", 27, 36, 1, 400, "inStore",7));
	    add(new Painting("Интуиция","Масло", 75, 65, 1, 80, "inStore", 10));
	    add(new Painting("Страст","Масло", 80, 80, 1, 320, "inStore", 1));
	    add(new Painting("Кръговрат","Масло", 100, 80, 1, 160, "inStore", 4));
	    add(new Painting("Скален обект","Графика", 10, 10, 1, 95, "inStore", 2 ));
	    add(new Painting("Форми","Графика", 10, 10, 1, 70, "inStore",1 ));
	    add(new Painting("Утеха","Графика", 92, 72, 1, 65, "inStore", 1));
	    add(new Painting("Формообразуване","Графика", 19, 15, 1, 65, "inStore", 2));
	    add(new Painting("Усещане","Графика", 19, 15, 1, 50, "inStore", 32));
	    add(new Painting("Спокойствие","Графика", 19, 4, 1, 60, "inStore", 26));
	    add(new Painting("Синхрон","Графика", 25, 30, 1, 130, "inStore", 5));
	    add(new Painting("Скален обект","Графика", 10, 10, 1, 95, "inStore", 5));
	    add(new Painting("Загадка","Графика", 10, 10, 1, 70, "inStore",13 ));
	    add(new Painting("Пирамида","Графика", 8, 4, 1, 50, "inStore", 13));
	    add(new Painting("Пространство","Графика", 10, 10, 1, 50, "inStore", 1));
	    add(new Painting("Мисъл","Акварел", 10, 10, 1, 50, "Sold", 3));
	    add(new Painting("Сакралност","Акварел", 100, 85, 1, 390, "Sold", 3));
	    add(new Painting("Изкушение","Акварел", 60, 80, 1, 50, "inStore", 3));
	    add(new Painting("Фриволност","Акварел", 55, 95, 1, 50, "inStore", 3));
	    add(new Painting("Оптимизъм","Графика", 80, 40, 1, 50, "inStore", 3));
	    add(new Painting("Спомен","Акварел", 100, 100, 1, 50, "inStore", 3));
	    add(new Painting("Женственост","Графика", 35, 45, 1, 50, "inStore", 13));
	    add(new Painting("Мъжественост","Акварел", 30, 60, 1, 50, "inStore", 1));
	    add(new Painting("Мирис на лято","Акварел", 50, 40, 1, 50, "inStore", 7));
	    add(new Painting("Импресия","Акварел", 10, 10, 1, 50, "inStore", 7));
	    add(new Painting("Мисъл","Акварел", 10, 10, 1, 50, "inStore", 8));
	    add(new Painting("Сакралност","Акварел", 100, 85, 1, 60, "inStore", 8));
	    add(new Painting("Изкушение","Акварел", 60, 80, 1, 150, "Available", 0));
	    add(new Painting("Фриволност","Акварел", 55, 95, 1, 400, "inStore", 8));
	    add(new Painting("Оптимизъм","Графика", 80, 40, 1, 260, "inStore", 8));
	    add(new Painting("Спомен","Акварел", 100, 100, 1, 550, "Available", 0));
	    add(new Painting("Женственост","Графика", 35, 45, 1, 220, "inStore", 9));
	    add(new Painting("Мъжественост","Акварел", 30, 60, 1, 320, "inStore", 9));
	    add(new Painting("Мирис на лято","Акварел", 50, 40, 1, 250, "inStore", 9));
	    add(new Painting("Импресия","Акварел", 10, 10, 1, 150, "inStore", 9));
	    add(new Painting("Истина","Графика", 80, 40, 1, 260, "inStore", 10));
	    add(new Painting("Класа","Масло", 100, 100, 1, 550, "Available", 0));
	    add(new Painting("Преходност","Графика", 35, 45, 1, 125, "Sold", 10));
	    add(new Painting("Eкспресия","Акварел", 30, 60, 1, 310, "inStore", 10));
	    add(new Painting("Душевност","Масло", 50, 40, 1, 290, "Sold", 10));
	    add(new Painting("Силата на мисълта","Акварел", 120, 100, 1, 150, "inStore", 10));
	    
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getGallery_id() {
		return gallery_id;
	}
	public void setGallery_id(int gallery_id) {
		this.gallery_id = gallery_id;
	}
	@Override
	public String toString() {
		return "Painting [id=" + id + ", name=" + name + ", technique=" + technique + ", height=" + height + ", width="
				+ width + ", fraim=" + fraim + ", price=" + price + ", state=" + state + ", gallery_id=" + gallery_id
				+ "]";
	}
}
