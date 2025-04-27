package bd.edu.seu.examlibrarymanagement.repository;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.model.BorrowRecord;
import bd.edu.seu.examlibrarymanagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    int countByMemberAndReturnedFalse(Member memberId);

//    List<BorrowRecord> findByMemberIdAndReturnedFalse(Member memberId);
    List<BorrowRecord> findByMemberId(int memberId);
    List<BorrowRecord> findByMember(Member member);
    List<BorrowRecord> findDistinctMembersByReturnedFalse();
    List<BorrowRecord> findDistinctMembersByReturnedFalseAndId(int id);
    List<BorrowRecord> findDistinctByReturnedFalse();
//    Optional<BorrowRecord> findByBookCopyIdAndMemberId(int bookCopyId, int memberId);
    List<BorrowRecord> findByReturnedFalse();
    Optional<BorrowRecord> findByBookCopyIsbnAndMemberId(int bookCopyId, int memberId);




}
