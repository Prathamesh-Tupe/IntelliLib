package Admin_Librarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_Librarian.entities.bookEntity;

@Repository
public interface booksRepository extends JpaRepository<bookEntity, String>{
    
	bookEntity findByBookUid(String bookUid);
}
