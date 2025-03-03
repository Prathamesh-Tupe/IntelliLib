package Admin_Librarian.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_language")
public class bookLanguageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_language_id;
	private String book_language_name;
	
	
	public bookLanguageEntity() { }


	public bookLanguageEntity(int book_language_id, String book_language_name) {
		super();
		this.book_language_id = book_language_id;
		this.book_language_name = book_language_name;
	}


	public int getBook_language_id() {
		return book_language_id;
	}


	public void setBook_language_id(int book_language_id) {
		this.book_language_id = book_language_id;
	}


	public String getBook_language_name() {
		return book_language_name;
	}


	public void setBook_language_name(String book_language_name) {
		this.book_language_name = book_language_name;
	}


	@Override
	public String toString() {
		return "bookLanguageEntity [book_language_id=" + book_language_id + ", book_language_name=" + book_language_name
				+ "]";
	}
}
