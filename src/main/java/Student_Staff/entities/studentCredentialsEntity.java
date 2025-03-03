package Student_Staff.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student_credentials")
public class studentCredentialsEntity {

	@Id
	@Column(name="credential_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int credentialId;
	
	@Column(name="student_uid")
	private String studentUid;
	
	@Column(name="student_username")
	private String studentUsername;
	
	@Column(name="student_password")
	private String studentPassword;

	public studentCredentialsEntity() { }

	public studentCredentialsEntity(int credentialId, String studentUid, String studentUsername,
			String studentPassword) {
		super();
		this.credentialId = credentialId;
		this.studentUid = studentUid;
		this.studentUsername = studentUsername;
		this.studentPassword = studentPassword;
	}

	public int getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(int credentialId) {
		this.credentialId = credentialId;
	}

	public String getStudentUid() {
		return studentUid;
	}

	public void setStudentUid(String studentUid) {
		this.studentUid = studentUid;
	}

	public String getStudentUsername() {
		return studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	@Override
	public String toString() {
		return "studentCredentialsEntity [credentialId=" + credentialId + ", studentUid=" + studentUid
				+ ", studentUsername=" + studentUsername + ", studentPassword=" + studentPassword + "]";
	}
}
