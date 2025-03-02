package Student_Staff.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Admin_Librarian.entities.borrowedBookEntity;
import Admin_Librarian.repositories.borrowedBooksRepository;
import Student_Staff.entities.studentCredentialsEntity;
import Student_Staff.entities.studentEntity;
import Student_Staff.repositories.studentCredentialsRepository;
import Student_Staff.repositories.studentRepository;

@Service
public class studentServiceLayer {

	@Autowired
	studentRepository studentRepo;
	
	@Autowired
	studentCredentialsRepository stuCredRepo;
	
	@Autowired
	borrowedBooksRepository borrowedRepo;
	
	public boolean saveStudent(studentEntity student) {
		try {
			student.setDate_registration(LocalDate.now());
			student.setEnd_registration(LocalDate.now().plusYears(1));
			student.setStatus("active");
			studentRepo.save(student);
			return true;
		} 
		catch (Exception e) { return false; }
	}
	
	
	public boolean saveCredentials(studentCredentialsEntity studentCred) {
		return (stuCredRepo.save(studentCred) != null)?true:false;
	}
	
	public studentEntity studentLogin(String studentUid, String studentUsername, String studentPassword ) {
		return (stuCredRepo.existsByStudentUidAndStudentUsernameAndStudentPassword(studentUid,studentUsername, studentPassword))
				?studentRepo.findById(studentUid).orElse(null):null;
	}
	
	public List<borrowedBookEntity> getStudentBooks(String StudentUid){
		return (borrowedRepo.findByStudentUidAndStatus(StudentUid,"borrowed") != null)
				?borrowedRepo.findByStudentUidAndStatus(StudentUid,"borrowed"):null;
	}
}
