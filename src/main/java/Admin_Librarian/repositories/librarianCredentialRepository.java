package Admin_Librarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_Librarian.entities.librarianCredentialEntity;

@Repository
public interface librarianCredentialRepository extends JpaRepository<librarianCredentialEntity, Integer>{
	boolean existsByLibrarianIdAndLibrarianUsernameAndLibrarianPassword(
			 int librarianId, String username, String password);}
