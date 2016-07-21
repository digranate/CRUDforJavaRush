package digranate.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name="USER")
public class Person {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@Column(name="isAdmin")
	@Type(type = "numeric_boolean")
	private boolean isAdmin;

    @Column(name = "createdDate",updatable = false)
	//@Type(type = "org.hibernate.type.TimestampType")
	@Temporal(TemporalType.DATE)
	private Date dateCreated;

	transient String dateCreatedString;

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

	public int getAge() {
		return age;
	}

	public void setIsAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean getIsAdmin() {

		return isAdmin;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person() {
	}

	public Person(int id, String name, int age, boolean isAdmin, Date dateCreated,String dateCreatedString) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.isAdmin = isAdmin;
		this.dateCreatedString = dateCreatedString;

		this.dateCreated = new Date();
		Calendar gc = new GregorianCalendar();
		gc.setTime(this.dateCreated);

		gc.set(Calendar.MILLISECOND, 0);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.HOUR_OF_DAY, 0);
		this.dateCreated = gc.getTime();

	}

	public Date getDateCreated() {
			return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateCreatedString() {
		return dateCreatedString;
	}

	public void setDateCreatedString(String dateCreatedString) {
		this.dateCreatedString = dateCreatedString;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", isAdmin=" + isAdmin +
				", dateCreated=" + dateCreated +
				'}';
	}
}
