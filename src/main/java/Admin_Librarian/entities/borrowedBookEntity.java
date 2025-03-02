package Admin_Librarian.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_borrowed_student")
public class borrowedBookEntity {
 
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(name="student_uid")
	private String studentUid;
	
	@Column(name="book_uid")
	private String bookUid;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate borrowed_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate expected_return_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate actual_return_date;
	
	private int fine;
	private String status;
	private String remark;
	
	@Column(name="librarian_id")
	private int librarianId;

	public borrowedBookEntity() {  }

	public borrowedBookEntity(String studentUid, String bookUid, LocalDate borrowed_date,
			LocalDate expected_return_date, LocalDate actual_return_date, int fine, String status, String remark,
			int librarianId) {
	
		//this.transactionId = transactionId;
		this.studentUid = studentUid;
		this.bookUid = bookUid;
		this.borrowed_date = borrowed_date;
		this.expected_return_date = expected_return_date;
		this.actual_return_date = actual_return_date;
		this.fine = fine;
		this.status = status;
		this.remark = remark;
		this.librarianId = librarianId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getStudentUid() {
		return studentUid;
	}

	public void setStudentUid(String studentUid) {
		this.studentUid = studentUid;
	}

	public String getBookUid() {
		return bookUid;
	}

	public void setBookUid(String bookUid) {
		this.bookUid = bookUid;
	}

	public LocalDate getBorrowed_date() {
		return borrowed_date;
	}

	public void setBorrowed_date(LocalDate borrowed_date) {
		this.borrowed_date = borrowed_date;
	}

	public LocalDate getExpected_return_date() {
		return expected_return_date;
	}

	public void setExpected_return_date(LocalDate expected_return_date) {
		this.expected_return_date = expected_return_date;
	}

	public LocalDate getActual_return_date() {
		return actual_return_date;
	}

	public void setActual_return_date(LocalDate actual_return_date) {
		this.actual_return_date = actual_return_date;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}

	@Override
	public String toString() {
		return "borrowedBookEntity [transactionId=" + transactionId + ", StudentUid=" + studentUid + ", bookUid="
				+ bookUid + ", borrowed_date=" + borrowed_date + ", expected_return_date=" + expected_return_date
				+ ", actual_return_date=" + actual_return_date + ", fine=" + fine + ", status=" + status + ", remark="
				+ remark + ", librarianId=" + librarianId + "]";
	}
}
