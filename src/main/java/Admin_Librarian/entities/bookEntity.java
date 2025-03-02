package Admin_Librarian.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class bookEntity {
 
	 @Id
	 @Column(name = "book_uid")
	 private String bookUid;
	 private String title;
	 private String author;
	 private String publisher;
	 private String edition;
	 private int    isbn_number; 
     
	 @ManyToOne
	 @JoinColumn(name = "genre_id", nullable = false)
	 private bookGenreEntity genre;
	 @ManyToOne
	 @JoinColumn(name="book_language_id", nullable=false)
	 private bookLanguageEntity book_language_id;
	 
	 private String shelf_location;
	 private int    book_price;
	 private String status;
	 
	 
	public bookEntity() {  }


	public bookEntity(String bookUid, String title, String author, String publisher, String edition, int isbn_number,
			bookGenreEntity genre, bookLanguageEntity book_language_id, String shelf_location, int book_price, String status) {
		super();
		this.bookUid = bookUid;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.isbn_number = isbn_number;
		this.genre = genre;
		this.book_language_id = book_language_id;
		this.shelf_location = shelf_location;
		this.book_price = book_price;
		this.status = status;
	}


	public String getBookUid() {
		return bookUid;
	}


	public void setBookUid(String bookUid) {
		this.bookUid = bookUid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getEdition() {
		return edition;
	}


	public void setEdition(String edition) {
		this.edition = edition;
	}


	public int getIsbn_number() {
		return isbn_number;
	}


	public void setIsbn_number(int isbn_number) {
		this.isbn_number = isbn_number;
	}


	public bookGenreEntity getGenre() {
		return genre;
	}


	public void setGenre(bookGenreEntity genre) {
		this.genre = genre;
	}


	public bookLanguageEntity getBook_language_id() {
		return book_language_id;
	}


	public void setBook_language_id(bookLanguageEntity book_language_id) {
		this.book_language_id = book_language_id;
	}


	public String getShelf_location() {
		return shelf_location;
	}


	public void setShelf_location(String shelf_location) {
		this.shelf_location = shelf_location;
	}


	public int getBook_price() {
		return book_price;
	}


	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "bookEntity [bookUid=" + bookUid + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", edition=" + edition + ", isbn_number=" + isbn_number + ", genre=" + genre + ", book_language_id="
				+ book_language_id + ", shelf_location=" + shelf_location + ", book_price=" + book_price + ", status="
				+ status + "]";
	}
}
