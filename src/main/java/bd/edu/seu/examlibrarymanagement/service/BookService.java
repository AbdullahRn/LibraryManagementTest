package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import bd.edu.seu.examlibrarymanagement.model.BorrowRecord;
import bd.edu.seu.examlibrarymanagement.repository.BookCopyRepository;
import bd.edu.seu.examlibrarymanagement.repository.BookRepository;
import bd.edu.seu.examlibrarymanagement.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BorrowRecord borrowRecord = new BorrowRecord();
    private final BorrowRecordService borrowRecordService;
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookCopyService bookCopyService;

    public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository,  BorrowRecordService borrowRecordService, BorrowRecordRepository borrowRecordRepository, BookCopyService bookCopyService) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.borrowRecordService = borrowRecordService;
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookCopyService = bookCopyService;
    }

//    public void save (Book book) {
//        for(int i =0; i<book.getNumberOfCopies(); i++){
//            BookCopy bookCopy = new BookCopy();
//            bookCopy.setBook(book);
//            bookCopyRepository.save(bookCopy);
//        }
//        bookRepository.save(book);
//    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public int getAvailableCopiesCount(int bookId) {
        return (int) bookCopyRepository.countByBookIdAndAvailableTrue(bookId);
    }

    public List<Book> findAll() {
        for(Book i : bookRepository.findAll()){
            System.out.println(i.getTitle());
        }
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


//        public List<Long> getBorrowRecordIdsByStudentId(int memberId) {
//            Book book = bookService.findByIsbn(memberId);
//            return borrowRecordRepository.findByMemberId(memberId)
//                    .stream()
//                    .map(BorrowRecord::getId)
//                    .collect(Collectors.toList());
//        }





        return filteredBooks;
    }


        public Book searchByTitle (String title) {
            Optional<Book> book  = bookRepository.findByTitle(title);
            return book.orElse(null);
        }




}
