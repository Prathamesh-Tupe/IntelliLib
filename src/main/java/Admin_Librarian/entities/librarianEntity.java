package Admin_Librarian.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="librarian")
public class librarianEntity {
 
	@Id
	@Column(name="librarian_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int librarianId;
	@Column(name="librarian_name")
	private String librarianName;
	private int age;
	private String email_id;
	private int phone_number;
	private String address;
	
	public librarianEntity() {	}

	public librarianEntity(int librarianId, String librarianName, int age, String email_id, int phone_number,
			String address) {
		super();
		this.librarianId = librarianId;
		this.librarianName = librarianName;
		this.age = age;
		this.email_id = email_id;
		this.phone_number = phone_number;
		this.address = address;
	}

	public int getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}

	public String getLibrarianName() {
		return librarianName;
	}

	public void setLibrarianName(String librarianName) {
		this.librarianName = librarianName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public int getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "librarianEntity [librarianId=" + librarianId + ", librarianName=" + librarianName + ", age=" + age
				+ ", email_id=" + email_id + ", phone_number=" + phone_number + ", address=" + address + "]";
	}
	
	
	
	
	
}
