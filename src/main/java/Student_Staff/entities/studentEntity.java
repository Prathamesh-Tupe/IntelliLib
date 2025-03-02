package Student_Staff.entities;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class studentEntity {

	@Id
	private String student_uid;
	private String student_first_name;
	private String student_last_name;
	private int    course_id;
	private int    roll_no;
	private String email_id;
	private long   phone_number;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate   DOB;
	private String address;
	private int    issued_book_count;
	private LocalDate   date_registration;
	private LocalDate   end_registration;
	private String status;
	
	
	public studentEntity() { }
	
	
	public studentEntity(String student_uid, String student_first_name, String student_last_name, int course_id,
			int roll_no, String email_id, long phone_number, LocalDate dOB, String address, int issued_book_count,
			LocalDate date_registration, LocalDate end_registration, String status) {
		super();
		this.student_uid = student_uid;
		this.student_first_name = student_first_name;
		this.student_last_name = student_last_name;
		this.course_id = course_id;
		this.roll_no = roll_no;
		this.email_id = email_id;
		this.phone_number = phone_number;
		DOB = dOB;
		this.address = address;
		this.issued_book_count = issued_book_count;
		this.date_registration = date_registration;
		this.end_registration = end_registration;
		this.status = status;
	}


	public String getStudent_uid() {
		return student_uid;
	}


	public void setStudent_uid(String student_uid) {
		this.student_uid = student_uid;
	}


	public String getStudent_first_name() {
		return student_first_name;
	}


	public void setStudent_first_name(String student_first_name) {
		this.student_first_name = student_first_name;
	}


	public String getStudent_last_name() {
		return student_last_name;
	}


	public void setStudent_last_name(String student_last_name) {
		this.student_last_name = student_last_name;
	}


	public int getCourse_id() {
		return course_id;
	}


	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}


	public int getRoll_no() {
		return roll_no;
	}


	public void setRoll_no(int roll_no) {
		this.roll_no = roll_no;
	}


	public String getEmail_id() {
		return email_id;
	}


	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}


	public long getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}


	public LocalDate getDOB() {
		return DOB;
	}


	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getIssued_book_count() {
		return issued_book_count;
	}


	public void setIssued_book_count(int issued_book_count) {
		this.issued_book_count = issued_book_count;
	}


	public LocalDate getDate_registration() {
		return date_registration;
	}


	public void setDate_registration(LocalDate date_registration) {
		this.date_registration = date_registration;
	}


	public LocalDate getEnd_registration() {
		return end_registration;
	}


	public void setEnd_registration(LocalDate end_registration) {
		this.end_registration = end_registration;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "studentEntity [student_uid=" + student_uid + ", student_first_name=" + student_first_name
				+ ", student_last_name=" + student_last_name + ", course_id=" + course_id + ", roll_no=" + roll_no
				+ ", email_id=" + email_id + ", phone_number=" + phone_number + ", DOB=" + DOB + ", address=" + address
				+ ", issued_book_count=" + issued_book_count + ", date_registration=" + date_registration
				+ ", end_registration=" + end_registration + ", status=" + status + "]";
	}
}
