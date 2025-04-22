package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Admin;
import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.service.BookService;
import bd.edu.seu.examlibrarymanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {
    private final BookService bookService;
    private final MemberService memberService;

    public DashboardController(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/dashboard")
    public String home(Model model){
        model.addAttribute("bookList", bookService.findAll());
        model.addAttribute("totalUser", memberService.getAll().size());
        List<Book> bookList = bookService.findAll();

        int totalCopies = bookList.stream().mapToInt(Book::getNumberOfCopies).sum();
        model.addAttribute("totalBooks", totalCopies);

        return "dashboard"; // Returns dashboard.html from templates/
    }

    @GetMapping("/userDashboard")
    public String userDasboard(Model model){
        model.addAttribute("member", new Member());
        return "userDashboard"; // Returns dashboard.html from templates/
    }

    @PostMapping("/books/search")
    public String objectSubmitForm(@RequestParam String query, Model model){

        List<Book> foundBooks = bookService.searchBooks(query);
        model.addAttribute("bookList", foundBooks);

        return "redirect:/dashboard";
    }



}
