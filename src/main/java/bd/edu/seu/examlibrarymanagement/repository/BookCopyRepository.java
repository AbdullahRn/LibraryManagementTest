package bd.edu.seu.examlibrarymanagement.repository;

import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
    Optional<BookCopy> findFirstByBookIdAndAvailableTrue(int bookId);
    int countByBookIdAndAvailableTrue(int bookId);
}
