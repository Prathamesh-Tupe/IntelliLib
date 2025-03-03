package Admin_Librarian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_Librarian.entities.bookLanguageEntity;

@Repository
public interface bookLanguageRepository extends JpaRepository<bookLanguageEntity, Integer>{

}
