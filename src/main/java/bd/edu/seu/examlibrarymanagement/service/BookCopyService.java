package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import bd.edu.seu.examlibrarymanagement.repository.BookCopyRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;

@Service
public class BookCopyService {
//    BookCopyRepository bookCopyRepository;
    BookCopyRepository bookCopyRepository;
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public BookCopy searchBookCopyById(int id) {
        return bookCopyRepository.findByBookId(id).orElse(null);
    }


}
