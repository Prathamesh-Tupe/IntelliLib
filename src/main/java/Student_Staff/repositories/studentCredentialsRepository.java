package Student_Staff.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Student_Staff.entities.studentCredentialsEntity;

@Repository
public interface studentCredentialsRepository extends JpaRepository<studentCredentialsEntity, Integer>{
    boolean existsByStudentUidAndStudentUsernameAndStudentPassword(String studentUid, String studentUsername, String studentPassword);
}
