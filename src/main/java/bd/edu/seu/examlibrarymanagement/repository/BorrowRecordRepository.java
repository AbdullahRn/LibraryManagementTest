package bd.edu.seu.examlibrarymanagement.repository;

import bd.edu.seu.examlibrarymanagement.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
}
