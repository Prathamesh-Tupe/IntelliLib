package Admin_Librarian.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="librarian_credentials")
public class librarianCredentialEntity {

	@Id
	@Column(name="credential_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int credentialId;
	
	@Column(name="librarian_id")
	private int librarianId;
	
	@Column(name="librarian_username")
	private String librarianUsername;
	
	@Column(name="librarian_password")
	private String librarianPassword;
	
	public librarianCredentialEntity() { }

	public librarianCredentialEntity(int credentialId, int librarianId, String librarianUsername,
			String librarianPassword) {
		this.credentialId = credentialId;
		this.librarianId = librarianId;
		this.librarianUsername = librarianUsername;
		this.librarianPassword = librarianPassword;
	}

	public int getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(int credentialId) {
		this.credentialId = credentialId;
	}

	public int getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}

	public String getLibrarianUsername() {
		return librarianUsername;
	}

	public void setLibrarianUsername(String librarianUsername) {
		this.librarianUsername = librarianUsername;
	}

	public String getLibrarianPassword() {
		return librarianPassword;
	}

	public void setLibrarianPassword(String librarianPassword) {
		this.librarianPassword = librarianPassword;
	}

	@Override
	public String toString() {
		return "librarianCredentialEntity [credentialId=" + credentialId + ", librarianId=" + librarianId
				+ ", librarianUsername=" + librarianUsername + ", librarianPassword=" + librarianPassword + "]";
	}
}
