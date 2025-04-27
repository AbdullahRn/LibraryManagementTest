package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.service.BookService;
import bd.edu.seu.examlibrarymanagement.service.BorrowRecordService;
import bd.edu.seu.examlibrarymanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminEditBook {

    private final BookService bookService;
    private final MemberService memberService;
    private final BorrowRecordService borrowRecordService;

    public AdminEditBook(BookService bookService, MemberService memberService, BorrowRecordService borrowRecordService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.borrowRecordService = borrowRecordService;
    }


    @GetMapping("/adminBookEdit")
    public String home(Model model){
        model.addAttribute("member", new Member());
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("book", new Book());

        List<Book> bookList = bookService.findAll();

        return "adminbookedit"; // Returns dashboard.html from templates/
    }

    @GetMapping("/adminMemberCreate")
    public String memberCreate(Model model){
        model.addAttribute("member", new Member());

        return "adminmembercreate";
    }

    @PostMapping("/members/search")
    public String objectSubmitForm(@RequestParam String query, Model model){
//        if(query == null || query.isEmpty()){
//            model.addAttribute("bookList", borrowRecordService.);
//        }


        Optional<Member> member = memberService.searchMember(query);
        List<BookCopy> takenBookList = borrowRecordService.listOfBooksTakenByMember(member.get().getId());

        model.addAttribute("bookList", takenBookList);

        return "adminManageBorrowing";
    }


    @PostMapping("/edit/submit")
    public String objectSubmitForm1(@ModelAttribute Book book, Model model){
//        Book books = bookService.searchByTitle(book.getTitle());
        Book books = bookService.searchByTitle(book.getTitle());


        if (books == null) {
            model.addAttribute("error", "No book found with title: " + book.getTitle());
            return "redirect:/adminBookEdit";
        }

        books.setTitle(book.getTitle());
        books.setGenere(book.getGenere());
        books.setPublicationYear(book.getPublicationYear());


        bookService.save(books);
        return "redirect:/adminBookEdit";
    }


    @PostMapping("/edit/add")
    public String objectSubmitForm2(@ModelAttribute Book book, Model model){
//        Book books = bookService.searchByTitle(book.getTitle());

        bookService.save(book);
        return "redirect:/adminBookEdit";
    }

    @PostMapping("/bookReturnProcedure")
    public String returnBook(@RequestParam("isbn") int isbn,  @RequestParam("memberId") int memberId) {
        borrowRecordService.returnBook(isbn, memberId);
        return "redirect:/adminManageBorrow";
    }





}
