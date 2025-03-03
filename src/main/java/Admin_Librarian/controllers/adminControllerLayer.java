package Admin_Librarian.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Student_Staff.entities.studentEntity;
import Student_Staff.service.studentServiceLayer;
import Admin_Librarian.entities.bookEntity;
import Admin_Librarian.entities.bookGenreEntity;
import Admin_Librarian.entities.borrowedBookEntity;
import Admin_Librarian.entities.librarianCredentialEntity;
import Admin_Librarian.entities.librarianEntity;
import Admin_Librarian.service.adminServiceLayer;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") 
public class adminControllerLayer {

	@Autowired
	adminServiceLayer ServiceLayer;
	String studentUid;
	String bookUid;
	studentEntity masterStudent;
	int librarianId;
	@Autowired
	studentServiceLayer service;
	
	
	// --------------- Controller for Authenticating & Login of Librarian -------------------//
	@PostMapping("/library/login")
	public ResponseEntity<Object> librarianLogin(@RequestBody librarianCredentialEntity credentials) {
		 try {
		        librarianEntity librarian = ServiceLayer.authenticateLibrarian(
		                credentials.getLibrarianId(), credentials.getLibrarianUsername(), credentials.getLibrarianPassword());
		        librarianId=librarian.getLibrarianId();
		        
		        if (librarian != null) {
		            return ResponseEntity.ok(librarian);
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		        }
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
		    }
	}
	
	
	// --------------- Controller for Admin to Add Books -------------------//
	@PostMapping("/library/admin/addbook")
	public Map<String,Boolean> addBooks(@RequestBody bookEntity book) {
		try { return Map.of("Message",ServiceLayer.addBook(book)); }
		catch (Exception e) { return Map.of("Message", false); }
	}
	
	
	// --------------- Controller for Admin to Update Books -------------------//
	@PostMapping("/library/librarian/updatebook")
	public Map<String,Boolean> updateBook(@RequestBody bookEntity book) {
		try { return Map.of("Message",ServiceLayer.updateBook(book)); }
		catch (Exception e) { return Map.of("Message", false); }
	}
	
	
	// **********************************************************************
	// --------------- Controller for Admin to Get Books -------------------//
	@GetMapping("/library/librarian/getbooks")
	public List<bookEntity> getbooks() {
		try {return ServiceLayer.getBooks(); } 
		catch (Exception e) { return null; } 
	}
	
	
	// --------------- Contorller Get Books by id -------------------//
		@GetMapping("/library/librarian/getbook/{bookUid}")
		public bookEntity getbookById(@PathVariable String bookUid){
			try {
				bookEntity book=ServiceLayer.getBook(bookUid);
				return book; 
				} 
			catch (Exception e) { return null; } 
		}
	
	// --------------- Controller for Librarian to Login student for borrowing book -------------------//
	@PostMapping("/library/librarian/borrow/getstudent")
	public ResponseEntity<Object> loginStudentByLibrarian(@RequestBody studentEntity student) {
		try {
		studentEntity stu=ServiceLayer.getstudent(student.getStudent_uid());
		masterStudent=stu;
		studentUid = stu.getStudent_uid();
			 return ResponseEntity.ok(stu);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
		    }
	}
	
	
	//--------------------- Controller for Frontend to get MasterStudent --------------- -------//
	@PostMapping("/library/librarian/borrow/getMasterstudent")
	public ResponseEntity<Object> getMasterStudent() {
		try {
			 return ResponseEntity.ok(masterStudent);
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
		    }
	}

	
	// **********************************************************************
	@PostMapping("/student/getborrowedBooks")
	public List<borrowedBookEntity> getStudentBooks() {
		try {
		return	service.getStudentBooks(studentUid);
		} catch (Exception e) { return null; }
	}
	
	// --------------- Controller for Librarian to save borrowed book or return book -------------------//
	@PostMapping("/library/librarian/borrow/getbook")
	public String borrowBookByStudent(@RequestBody bookEntity book) {
		if(studentUid == null )
			return "Please Login Student First";
		
		boolean result=ServiceLayer.isBookBorrowed(studentUid,book.getBookUid());
		if(result) {
			return "Returned";
		}else {
			bookUid=book.getBookUid();
			return ServiceLayer.saveBorrowedtransaction(studentUid,book.getBookUid(),librarianId);
		}
	}
	
	
	// **********************************************************************
	// --------------- Controller for Librarian to get all past transaction -------------------//
		@GetMapping("/library/librarian/borrow/pasttransaction")
		public List<borrowedBookEntity> getAllPastTransaction() {
		  return ServiceLayer.getPastTransaction();
		}
		
		
		// --------------- Controller for getting popular books -------------------//
		@GetMapping("/library/librarian/borrow/getpopularbooks")
		public List<String> getPopularBooks() {
		return ServiceLayer.getPopBooks(bookUid);
		}	
		
	
	// --------------- Controller for Librarian to get all past transaction By status -------------------//
		@GetMapping("/library/librarian/borrow/pasttransaction/{status}")
		public List<borrowedBookEntity> getPastTransactionByStatus(@PathVariable String status){
		  return ServiceLayer.getPastTransactionByStatus(status);

		}
		
		
	//--------------- Controller for to get All student ----------------//
	@GetMapping("/library/librarian/getStudent")
		public List<studentEntity> getAllStudent(){
			try {
				List<studentEntity> listStudent=ServiceLayer.getAllStudent();
				return listStudent;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		
		
		//--------------- Controller for to get All Issued Books ----------------//
		@GetMapping("/library/librarian/getissuedbooks")
			public List<borrowedBookEntity> getIssuedBooks(){
				try {
					List<borrowedBookEntity> listIssuedBook=ServiceLayer.getAllIssuedBooks("borrowed");
					return listIssuedBook;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return null;
				}
			}
			
			
			//--------------- Controller for to get All overdue Books ----------------//
			@GetMapping("/library/librarian/getoverduebooks")
				public List<borrowedBookEntity> getAllOverdueBooks(){
					try {
						List<borrowedBookEntity> listIssuedBook=ServiceLayer.getAllOverdueBooks("overdue");
						
						return listIssuedBook;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						return null;
					}
				}
}
