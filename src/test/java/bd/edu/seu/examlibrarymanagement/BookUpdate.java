package bd.edu.seu.examlibrarymanagement;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.service.BookService;
import bd.edu.seu.examlibrarymanagement.service.AdminService;
import bd.edu.seu.examlibrarymanagement.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class BookUpdate {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;


//    @Test
//    public void UpdateBook(){
//        BookService bookService = null;
//        Book book = bookService.findByIsbn("400");
//
//        book.setGenere("Thriller");
//        book.setPublicactionYear();
//        book.setNumberOfCopies(book.getNumberOfCopies()-1);
//        bookService.save(book);
//    }

    @Test
    public void BookSave(){
        Book book = new Book();
        book.setIsbn("600");
        book.setTitle("Something");
        book.setNumberOfCopies(20);
        book.setAuthors(new ArrayList<>());
        book.getAuthors().add("GGGGG");
        book.getAuthors().add("SSSSSS");
        bookService.save(book);
    }


}
