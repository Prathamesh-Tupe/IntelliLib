package Student_Staff.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Admin_Librarian.entities.borrowedBookEntity;
import Student_Staff.entities.studentCredentialsEntity;
import Student_Staff.entities.studentEntity;
import Student_Staff.service.studentServiceLayer;

@RestController 
public class studentControllerLayer {

	@Autowired
	studentServiceLayer service;
	String StudentUid;
	
	//--------------- Controller for student for registration ----------------//
	@PostMapping("/library/student/addstudent")
	public boolean addStudent(@RequestBody studentEntity student){
		try{ return service.saveStudent(student); }
		catch (Exception e){ return false; }
	}
	
    
	//--------------- Controller for student for Set Username and password ----------------//
	@PostMapping("/library/student/setcredentials")
	public boolean setcredentials(@RequestBody studentCredentialsEntity studentCred){
		try{ return service.saveCredentials(studentCred); }
		catch (Exception e){ return false; }
	}
	
	
	//--------------- Controller for student for login ----------------//
	@PostMapping("/student/login")
	public studentEntity studentLogin(@RequestBody studentCredentialsEntity credentials) {
	  try {	
		    StudentUid=credentials.getStudentUid();
		  return (service.studentLogin
			  (credentials.getStudentUid(),credentials.getStudentUsername(), credentials.getStudentPassword()));}
	  catch (Exception e) { return null;}
	}
	
	//--------------- Controller for student to see borrowed books ----------------//
		@PostMapping("/student/getborrowedbooks")
		public List<borrowedBookEntity> getStudentBooks() {
			try {System.out.println(StudentUid);
			return	service.getStudentBooks(StudentUid);
			} catch (Exception e) { return null; }
		}
		
}
