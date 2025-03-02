package Student_Staff.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Student_Staff.entities.studentEntity;

@Repository
public interface studentRepository extends JpaRepository<studentEntity, String>{

}
