package bd.edu.seu.examlibrarymanagement.repository;

import bd.edu.seu.examlibrarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);
    Optional<Book> findByTitle(String title);
}
