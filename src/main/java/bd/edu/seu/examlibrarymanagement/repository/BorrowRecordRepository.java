package bd.edu.seu.examlibrarymanagement.repository;

import bd.edu.seu.examlibrarymanagement.model.BorrowRecord;
import bd.edu.seu.examlibrarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    int countByMemberAndReturnedFalse(Member memberId);

    List<BorrowRecord> findByMemberIdAndReturnedFalse(Member memberId);
    List<BorrowRecord> findByMemberId(int memberId);
    List<BorrowRecord> findByMember(Member member);

}
