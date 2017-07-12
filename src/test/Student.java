package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Student {
	private int id;
	private Integer facultyNumber;
	private String firstName;
	private String secondName;
	private String lastName;
	private String EGN;
	
	public static ArrayList<Student> students = new ArrayList<Student>() {{
	    add(new Student("Maria", "Dimitrova", "Atanasova"));
	    add(new Student("Aneliq", "Angelova", "Dineva"));
	    add(new Student("Nikolay", "Dimitrov", "Kirilov"));
	    add(new Student("Pavel", "Stoqnov", "Hristov"));
	    add(new Student("Tony", "Rumenov", "Dinev"));
	    add(new Student("Stanka", "Dimitrova", "Atanasova"));
	    add(new Student("Denica", "Ivanova", "Angelova"));
	    add(new Student("Stoqn", "Dimitrov", "Zurlev"));
	    add(new Student("Plamen", "Hristov", "Hristov"));
	    add(new Student("Svetla", "Nikodimova", "Draganova"));
	    add(new Student("Orlin", "Simeonov", "Zlatev"));
	    add(new Student("Tania", "Dimitrova", "Daskalova"));
	    add(new Student("Rosen", "Georgiev", "Karpuzov"));
	    add(new Student("Venelina", "Georgieva", "Dimitrova"));
	    add(new Student("Liubomir", "Dimitrov", "Grigorov"));
	    add(new Student("Veselina", "Zlateva", "Rahneva"));
	    add(new Student("Atanas", "Hristov", "Zlatarev"));
	    add(new Student("Konstantiva", "Nikolava", "Maneva"));
	    add(new Student("Boryana", "Dimcheva", "Paskova"));
	    add(new Student("Iskren", "Mavrov", "Pushkarov"));
	    add(new Student("Sami", "Ahmed", "Hamid"));
	    add(new Student("Dilyana", "Georgieva", "Stoeva"));
	    add(new Student("Elica", "Hristova", "Peltekova"));
	    add(new Student("Violina", "Stefan", "Kodeva"));
	    add(new Student("Paolina", "Georgieva", "Georgieva"));
	    add(new Student("Stoyanka", "Milusheva", "Djinovska"));
	    add(new Student("Kaloyan", "Brezeliev", "Hristov"));
	    add(new Student("Dimitrina", "Trendafilova", "Trendafilova"));
	    add(new Student("Teodora", "Atanasova", "Stancheva"));
	    add(new Student("Anna", "Odadjieva", "Kisova"));
	    add(new Student("Krasimira", "Dimitrova", "Cvetanova"));
	    add(new Student("Snejana", "Darakelova", "Kirilova"));
	    
	    
	    add(new Student("Boriyana", "Rumenova", "Cholakova"));
	    add(new Student("Vesela", "Ivanova", "Grigorova"));
	    add(new Student("Diqna", "Ilieva", "Kaneva"));
	    add(new Student("Andrei", "Veselinov", "Kotelnikov"));
	    add(new Student("Jana", "Darakelova", "Iordanova"));
	    add(new Student("Silvana", "Ivanova", "Kuceva"));
	    add(new Student("Slaveya", "Stoyanova", "Nedelcheva"));
	    add(new Student("Vasil", "Rumenov", "Dinev"));
	    add(new Student("Liliya", "Racheva", "Aleksieva"));
	    add(new Student("Ivan", "Konstantinov", "Aleksandrov"));
	    add(new Student("Iglika", "Dimitrova", "Stoevska"));
	    
	}};
	Random rand = new Random();
	
	public Student(String firstName, String secondName, String lastName) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		setFacultyNumber(Math.random());
		setEGN();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getFacultyNumber() {
		return facultyNumber;
	}
	public void setFacultyNumber(Double id) {
		int  n = rand.nextInt(45632) + 1;
		this.facultyNumber = (int) (18360000 + (n));
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEGN() {
		int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		int day = ThreadLocalRandom.current().nextInt(10, 30 + 1);
		int lastFour = ThreadLocalRandom.current().nextInt(65361, 96135);
		this.EGN = String.format("90%d%d%d", month, day, lastFour);
		return EGN;
	}
	public void setEGN() {
		int month = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		int day = ThreadLocalRandom.current().nextInt(1, 30 + 1);
		int lastFour = ThreadLocalRandom.current().nextInt(65361, 96135);
		this.EGN = String.format("90%s%d%d", ((month<10)?("0"+month):month)+"", day, lastFour);
	}
	

}
