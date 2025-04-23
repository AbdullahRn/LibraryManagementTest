package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Admin;
import bd.edu.seu.examlibrarymanagement.model.Book;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    public static Member loginMember = new Member();
    private final BookService bookService;
    private final MemberService memberService;
    private final BorrowRecordService borrowRecordService;

    public DashboardController(BookService bookService, MemberService memberService, BorrowRecordService borrowRecordService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("/dashboard")
    public String home(Model model){
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("totalUser", memberService.getAll().size());
        List<Book> bookList = bookService.findAll();

//        int totalCopies = bookList.stream().mapToInt(Book::getNumberOfCopies).sum();
//        model.addAttribute("totalBooks", totalCopies);

        return "dashboard";
    }

//    @GetMapping("/userDashboard")
//    public String userDasboard(Model model){
//        model.addAttribute("member", new Member());
//        return "userDashboard";
//    }




    @PostMapping("/books/search")
    public String objectSubmitForm(@RequestParam String query, Model model){
        if(query == null || query.isEmpty()){
            model.addAttribute("bookList", new ArrayList<>());
        }

        List<Book> foundBooks = bookService.searchBooks(query);

        model.addAttribute("bookList", foundBooks);

        return "dashboard";
    }

    @GetMapping("/userDashboard")
    public String home1(Model model){
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("book", new Book());
        model.addAttribute("totalUser", memberService.getAll().size());
        List<Book> bookList = bookService.findAll();

//        int totalCopies = bookList.stream().mapToInt(Book::getNumberOfCopies).sum();
//        model.addAttribute("totalBooks", totalCopies);

        return "userDashboard";
    }


    @PostMapping("/bookTaken/submit")
    public String objectSubmitForm2(@ModelAttribute Book book, Model model) {

        Book books = bookService.findByIsbn(book.getIsbn());

        int id = loginMember.getId().intValue();
        borrowRecordService.BorrowBook(id, book.getId());


        return "redirect:/userDashboard";
    }





}
