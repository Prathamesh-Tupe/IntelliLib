package Admin_Librarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_Librarian.entities.bookGenreEntity;

@Repository
public interface bookGenreRepository extends JpaRepository<bookGenreEntity, Integer>{
  
}
