package Admin_Librarian.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Admin_Librarian.entities.bookEntity;
import Admin_Librarian.entities.bookGenreEntity;
import Admin_Librarian.entities.bookLanguageEntity;
import Admin_Librarian.entities.borrowedBookEntity;
import Admin_Librarian.entities.librarianEntity;
import Admin_Librarian.repositories.bookGenreRepository;
import Admin_Librarian.repositories.bookLanguageRepository;
import Admin_Librarian.repositories.booksRepository;
import Admin_Librarian.repositories.borrowedBooksRepository;
import Admin_Librarian.repositories.librarianCredentialRepository;
import Admin_Librarian.repositories.librarianRepository;
import Student_Staff.entities.studentEntity;
import Student_Staff.repositories.studentRepository;


@Service
public class adminServiceLayer {
	@Autowired
	booksRepository bookRepo;
	
	@Autowired
	studentRepository studentRepo;
	
	@Autowired
	borrowedBooksRepository borrowedBookRepo;
	
	@Autowired
	librarianCredentialRepository librarianCredRepo;
	
	@Autowired
	librarianRepository librarianRepo;
	
	@Autowired
	bookGenreRepository bookGenRepo;
	
	@Autowired
	bookLanguageRepository bookLangRepo;
	
	private final Map<String, String> popBook = new LinkedHashMap<>(16, 0.80f, true);
	
	// --------------- Method Authenticate & Login Librarian -------------------//
	public librarianEntity authenticateLibrarian(int librarianId,String username,String password) {
		return (librarianCredRepo.existsByLibrarianIdAndLibrarianUsernameAndLibrarianPassword(librarianId,username, password))
				?librarianRepo.findById(librarianId).orElse(null):null;
	}
	
	
	// --------------- Method to Save book -------------------//
	public boolean addBook(bookEntity book) {
   try {
	bookLanguageEntity bookLang=bookLangRepo.findById(book.getBook_language_id().getBook_language_id()).orElseGet(null);
	 bookGenreEntity bookGenre=bookGenRepo.findById(book.getGenre().getGenre_id()).orElse(null);
	 if(bookGenre == null || bookLang == null) {
		 return false;
	 }
		      book.setBook_language_id(bookLang);
			  book.setGenre(bookGenre);
			 return bookRepo.save(book) != null; 
			  }
		  catch (Exception e) { return false; }
	}
	
	
	// --------------- Method to Update book -------------------//
	public boolean updateBook(bookEntity book) {
		try { return bookRepo.save(book) != null; }
		catch (Exception e) { return false; }
	}
	
	
	// --------------- Method to get book -------------------//
	public List<bookEntity> getBooks() {
		try { return bookRepo.findAll(); }
		catch (Exception e) {return null;}
	}
	
	
	// --------------- Method to get Student -------------------//
	public studentEntity getstudent(String student_uid) {
		try {
			return studentRepo.findById(student_uid).orElse(null);
		} catch (Exception e) {
			return null;
		}	
	}
	
	
	// --------------- Method to Get book by ID -------------------//
	public bookEntity getBook(String book_uid) {
		try { return bookRepo.findById(book_uid).orElse(null); } 
		catch (Exception e) { return null; }
	}
	
	
	// --------------- Method to Save borrowing transaction -------------------//
	public String saveBorrowedtransaction(String studentUid,String bookUid,int librarianId) {
		try {
			 studentEntity student=studentRepo.findById(studentUid).orElseGet(null);
			 if(student.getIssued_book_count() < 3) {
				 bookEntity book=bookRepo.findById(bookUid).orElse(null);
				 book.setStatus("issued");
				 bookRepo.save(book);
				 student.setIssued_book_count((student.getIssued_book_count()+1));
				 studentRepo.save(student);
				 if(!popBook.containsKey(bookUid)) {
						popBook.put(bookUid, bookRepo.getById(bookUid).getTitle());
					}
				 popBook.get(bookUid);
				 borrowedBookRepo.save(new borrowedBookEntity(studentUid, bookUid, LocalDate.now(), LocalDate.now().plusDays(7), null, 0, "borrowed", null, 2));
				 return "Borrowed";
			 }else   {
                return "Cannot borrow more than 3 books";      
			  }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "Something went wrong";
		}
	}
	
	
	// --------------- Method to Return book -------------------//
	public boolean isBookBorrowed(String studentUid, String bookUid) {
		 try {
			 List<String> statuses = Arrays.asList("borrowed", "overdue");
			   borrowedBookEntity transaction =  borrowedBookRepo.findByStudentUidAndBookUidAndStatusIn(studentUid, bookUid,statuses);
		       if(transaction != null) {
		    	 transaction.setStatus("returned");
		    	 transaction.setActual_return_date(LocalDate.now());
		    	 transaction.setFine(0);
		    	 borrowedBookRepo.save(transaction);
		    	 bookEntity book=bookRepo.findByBookUid(bookUid);
		    	 book.setStatus("available");
		    	 bookRepo.save(book);
		    	 studentEntity student=studentRepo.findById(studentUid).orElseGet(null);
		    	 student.setIssued_book_count((student.getIssued_book_count()-1));
				 studentRepo.save(student);
		    	 return true;
		       }
		       else {
		    	   return false;
		       }
		} catch (Exception e) {
			return false;
		} 
	}
	
	
	//---------------------- Get Popular books -----------------------//
	public List<String> getPopBooks(String bookUid) {
	List<String> popBookList=new ArrayList<String>();
	 for(Map.Entry<String, String> entry:popBook.entrySet()) {
		 popBookList.add(entry.getValue());
	 }
	 return popBookList;
	}
	
	
	// --------------- Method to Get All past transaction -------------------//
		public List<borrowedBookEntity > getPastTransaction() {
			try { return borrowedBookRepo.findAll(); } 
			catch (Exception e) { return null; }
		}
		
		
	// --------------- Method to Get All past transaction By Status -------------------//
       public List<borrowedBookEntity > getPastTransactionByStatus(String status) {
	    	try { return borrowedBookRepo.findByStatus(status); } 
	    	catch (Exception e) { return null; }
		}
       
   // 
     public List<studentEntity> getAllStudent(){
    	try {
    		 List<studentEntity> list=studentRepo.findAll();
        	 return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
     }
     
     
  // --------------- Method to Get All issued books -------------------//
     public List<borrowedBookEntity> getAllIssuedBooks(String status){
    	 try {
			List<borrowedBookEntity> listBooks=borrowedBookRepo.findByStatus(status);
			return listBooks;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
     }
     
     
  // --------------- Method to Get All overdue books -------------------//
     public List<borrowedBookEntity> getAllOverdueBooks(String status){
    	 try {
    		 List<borrowedBookEntity> listBook=borrowedBookRepo.findAll();
    		 for(borrowedBookEntity b:listBook) {
    			 
    			 if((LocalDate.now()).isAfter(b.getExpected_return_date()) && (b.getStatus().equals("borrowed"))) {
    				int daysBetween =(int) ChronoUnit.DAYS.between(b.getExpected_return_date(),LocalDate.now());
    				b.setFine(daysBetween*40);
    				
    				b.setStatus("overdue");
    				borrowedBookRepo.save(b); 
    			 }
    		 }
    		 
			List<borrowedBookEntity> listBooks=borrowedBookRepo.findByStatus(status);
			return listBooks;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
     }
}