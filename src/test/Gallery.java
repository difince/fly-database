package test;

import java.util.ArrayList;

public class Gallery {
	private int id;
	private String name;

	private int townId;
	private String contactName;
	private String address;
	private String details;
	private String telephone;
	
	public Gallery(String name, int townId,  String address, String contactName, String telephone, String details) {
		this.name = name;
		this.townId = townId;
		this.address = address;
		this.contactName = contactName;
		this.details = details;
		this.telephone = telephone;
	}
	
	public static ArrayList<Gallery> gallerys = new ArrayList<Gallery>() {{
	    add(new Gallery("Ной", 1, "ул. Париш 4-6", String.format("%s %s %s", "Maria", "Dimitrova", "Atanasova"),"+359 889 456 235", ""));
	    add(new Gallery("Krismar", 1, "ул. Гурко 3", String.format("%s %s %s", "Aneliq", "Angelova", "Dineva"),"+359 877 466 205", ""));
	    add(new Gallery("Logan", 1, "ул. Оборище 16", String.format("%s %s %s", "Nikolay", "Dimitrov", "Kirilov"),"+359 879 236 705", ""));
	    add(new Gallery("Карурси", 1, "ул. Хан Крум 4", String.format("%s %s %s", "Pavel", "Stoqnov", "Hristov"),"+359 877 506 123", ""));
	    add(new Gallery("Квадрат 500", 1, "ул. 19-ти Февруари 25", String.format("%s %s %s", "Tony", "Rumenov", "Dinev"),"+359 877 601 222", ""));
	    add(new Gallery("Маестро", 1, "ул. Цар Самуил 37", String.format("%s %s %s", "Stanka", "Dimitrova", "Atanasova"),"+359 877 449 390", ""));
	    add(new Gallery("Георги Папазов", 4, "ул.Цар Самуил 2", String.format("%s %s %s", "Stoqn", "Dimitrov", "Zurlev"),"+359 887 032 105", ""));
	    add(new Gallery("Арт Галерия - Стойчев", 4, "ул. Георги С. Раковски 5", String.format("%s %s %s", "Plamen", "Hristov", "Hristov"),"+359 877 974 562", ""));
	    add(new Gallery("Париш", 4, "ул. Цар Самуил 47", String.format("%s %s %s", "Svetla", "Nikodimova", "Draganova"),"+359 888 326 785", ""));
	    add(new Gallery("Romfeya", 4, "бул. Марица 83", String.format("%s %s %s", "Orlin", "Simeonov", "Zlatev"),"046 6466951", ""));
	    add(new Gallery("Възраждане", 2, "ул. Чалакож 1", String.format("%s %s %s", "Tania", "Dimitrova", "Daskalova"),"061 1148695", ""));
	    add(new Gallery("Аспект", 2, "ул. Стефан Стамболов 5", String.format("%s %s %s", "Rosen", "Georgiev", "Karpuzov"),"026548695", ""));
	    add(new Gallery("CAN CHRISTINA ANDROULIDAKI GALLERY", 7, "Panagiotou Anagnostopoulou 42", String.format("%s %s %s", "Venelina", "Georgieva", "Dimitrova"),"+30 21 0339 0833", ""));
	    add(new Gallery("Дега", 1, "ул. Княз Борис I 96", String.format("%s %s %s", "Liubomir", "Dimitrov", "Grigorov"),"+359 878 733 309", ""));
	    add(new Gallery("heliumcowboy artspace", 9, "Bäckerbreitergang 75", String.format("%s %s %s", "Veselina", "Zlateva", "Rahneva"),"+359 888 950 800", ""));
	    add(new Gallery("Arte", 1, "ул. Г. С. Раковски 183А", String.format("%s %s %s", "Atanas", "Hristov", "Zlatarev"),"02 698 647", ""));
	    add(new Gallery("Pashmin Art Gallery", 9, "Heilwigstraße 64", String.format("%s %s %s", "Konstantiva", "Nikolava", "Maneva"),"026548695", ""));
	    add(new Gallery("Меланит", 2, "ул. Стефан Веркович 10", String.format("%s %s %s", "Boryana", "Dimcheva", "Paskova"),"+359 877 980 395", ""));
	    add(new Gallery("Резонанс", 2, "ул. Петър Парчевич 19", String.format("%s %s %s", "Iskren", "Mavrov", "Pushkarov"),"+359 876 450 305", ""));
	    add(new Gallery("Ной", 2, "ул. Париш 25", String.format("%s %s %s", "Sami", "Ahmed", "Hamid"),"026548695", ""));
	    add(new Gallery("Виа Артис", 4, "ул. Славянска 19", String.format("%s %s %s", "Dilyana", "Georgieva", "Stoeva"),"+359 877 782 395", ""));
	    add(new Gallery("А+Галерия", 4, "ул. Загреб 18", String.format("%s %s %s", "Elica", "Hristova", "Peltekova"),"+359 888 562 212", ""));
	    add(new Gallery("Ной", 4, "ул. Париш 25", String.format("%s %s %s", "Paolina", "Georgieva", "Georgieva"),"026548695", ""));
	    add(new Gallery("Мастино", 4, "ул. Криволак 20", String.format("%s %s %s", "Stoyanka", "Milusheva", "Djinovska"),"+359 877 648 222", ""));
	    add(new Gallery("Mall-Galerry", 8, "MAll in Bourgas", String.format("%s %s %s", "Kaloyan", "Brezeliev", "Hristov"),"026548695", ""));
	    add(new Gallery("Арт Галерия - Ети", 8, "ул. Георги Кирков 21", String.format("%s %s %s", "Dimitrina", "Trendafilova", "Trendafilova"),"+359 877 641 328", ""));
	    add(new Gallery("Europe", 1, "ул. Леге 3", String.format("%s %s %s", "Teodora", "Atanasova", "Stancheva"),"+359 877 469 379", ""));
	    add(new Gallery("Пролет", 8, "ул. Абоба 13", String.format("%s %s %s", "Anna", "Odadjieva", "Kisova"),"+359 877 601 227 119", ""));
	    add(new Gallery("Борис Георгиев", 3, "ул. Любен Каравелов 1", String.format("%s %s %s", "Krasimira", "Dimitrova", "Cvetanova"),"+359 879 670 000", ""));
	    add(new Gallery("Маркони", 3, "ул. Охрид 22", String.format("%s %s %s", "Snejana", "Darakelova", "Kirilova"),"+359 888 975 362", ""));
	    add(new Gallery("Галерия 8", 3, "ул. Херман Шкорпил 8", String.format("%s %s %s", "Boriyana", "Rumenova", "Cholakova"),"+359 877 987 934", ""));
	    add(new Gallery("Ной", 5, "ул. Париш 25", String.format("%s %s %s", "Vesela", "Ivanova", "Grigorova"),"+359 877 446 207", ""));
	    add(new Gallery("Аросита", 1, "ул. Врабча 12", String.format("%s %s %s", "Diqna", "Ilieva", "Kaneva"),"02 654896", ""));
	    add(new Gallery("Актив", 3, "ул. Петко Тодоров 28", String.format("%s %s %s", "Andrei", "Veselinov", "Kotelnikov"),"+359 877 892 236", ""));
	    add(new Gallery("Теди", 3, "ул.  Юджин Скайлер 8", String.format("%s %s %s", "Jana", "Darakelova", "Iordanova"),"+359 877 567 123", ""));
	    add(new Gallery("Палитра", 7, "ул. К. Ганчев 64", String.format("%s %s %s", "Silvana", "Ivanova", "Kuceva"),"+359 886 601 876", ""));
	    add(new Gallery("Галерия 8", 7, "ул. Раковски 8", String.format("%s %s %s", "Slaveya", "Stoyanova", "Nedelcheva"),"+359 877 601 222", ""));
	    add(new Gallery("Георги Баев", 8, "ул. Демокрация 6", String.format("%s %s %s", "Vasil", "Rumenov", "Dinev"),"+359 877 678 970", ""));
	    add(new Gallery("Дари Арт", 3, "ул. Стефан Караджа 28", String.format("%s %s %s", "Liliya", "Racheva", "Aleksieva"),"+359 899 331 253", ""));
	    add(new Gallery("Станка Димитрова", 5, "ул. Републиканска 26", String.format("%s %s %s", "Ivan", "Konstantinov", "Aleksandrov"),"+359 889 999 222", ""));
//	    add(new Gallery("Ларго", 3, "ул. Хан Крум 8", String.format("%s %s %s", "Iglika", "Dimitrova", "Stoevska"),"+359 889 656 232", ""));
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

	public int getTownId() {
		return townId;
	}

	public void setTownId(int townId) {
		this.townId = townId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "Gallery [id=" + id + ", name=" + name + ", townId=" + townId + ", contactName=" + contactName
				+ ", address=" + address + ", details=" + details + ", telephone=" + telephone + "]";
	}
}
