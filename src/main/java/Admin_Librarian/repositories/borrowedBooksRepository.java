package Admin_Librarian.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Admin_Librarian.entities.borrowedBookEntity;


@Repository
public interface borrowedBooksRepository extends JpaRepository<borrowedBookEntity, Integer>{
	borrowedBookEntity findByStudentUidAndBookUidAndStatus(String studentUid,String bookUid,String status);
	List<borrowedBookEntity> findByStatus(String status);
	List<borrowedBookEntity> findByStudentUidAndStatus(String studentUid,String status);
    borrowedBookEntity findByStudentUidAndBookUidAndStatusIn(String studentUid, String bookUid, List<String> statuses);

}
