package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Book> books = bookRepository.findAll();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book i : books) {
            String temp = "";
            temp = temp.concat(Objects.toString(i.getIsbn(), ""));
            temp = temp.concat(Objects.toString(i.getIsbn(), ""));
            temp = temp.concat(Objects.toString(i.getGenere(), ""));
            temp = temp.concat(Objects.toString(i.getTitle(), ""));
            temp = temp.concat(Objects.toString(i.getPublicationYear(), ""));

            for(String j : i.getAuthors()) {
                temp = temp.concat(Objects.toString(j, ""));
            }

            if (temp.toLowerCase().contains(keyword.toLowerCase())) {
                filteredBooks.add(i);
            }
        }



        return filteredBooks;
    }



}
