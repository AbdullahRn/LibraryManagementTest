package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save (Book book) {
        bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByIsbn(String isbn) {
        Optional<Book> book  = bookRepository.findByIsbn(isbn);
        return book.orElse(null);
    }

//    List<Book> findBookList(String search){
//        return List<Book> bookRepository.findByTitleContainingIgnoreCase(String search);
//    };

    public List<Book> searchBooks(String keyword) {
        List<Book> books = bookRepository.findAll();
        List<Book> filteredBooks = new ArrayList<>();
        for(Book i: books){
            String temp = i.getIsbn();
            temp.concat(i.getGenere());
            temp.concat(i.getTitle());
            temp.concat(String.valueOf(i.getNumberOfCopies()));
            if(temp.contains(keyword)){
                filteredBooks.add(i);
            }

        }
        return filteredBooks;
    }



}
