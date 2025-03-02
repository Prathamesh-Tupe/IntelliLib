package Admin_Librarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_Librarian.entities.librarianEntity;

@Repository
public interface librarianRepository extends JpaRepository<librarianEntity, Integer>{
	
}
