package Admin_Librarian.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="genre")
public class bookGenreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int genre_id;
	private String genre_name;
	
	public bookGenreEntity() { }

	public bookGenreEntity(int genre_id, String genre_name) {
		this.genre_id = genre_id;
		this.genre_name = genre_name;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

	@Override
	public String toString() {
		return "bookGenreEntity [genre_id=" + genre_id + ", genre_name=" + genre_name + "]";
	}
	
	
}
