package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.*;
import bd.edu.seu.examlibrarymanagement.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static bd.edu.seu.examlibrarymanagement.controller.DashboardController.loginMember;

@Controller
public class WebController {
    private final MemberService memberService;
    private final AdminService adminService;
    private final BookService bookService;
    private final BookCopyService bookCopyService;
    private final BorrowRecordService borrowRecordService;

    public WebController(MemberService memberService, AdminService adminService, BookService bookService, BookCopyService bookCopyService, BorrowRecordService borrowRecordService) {
        this.memberService = memberService;
        this.adminService = adminService;
        this.bookService = bookService;
        this.bookCopyService = bookCopyService;
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("member", new Member());
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("member", new Member());
        return "index";
    }


    @PostMapping("/submit")
    public String objectSubmitForm(@ModelAttribute Member member){

        Member memberCheck =memberService.getByEmail(member.getEmail());
        Admin adminCheck = adminService.getByEmail(member.getEmail());

        if(adminCheck != null && adminCheck.getPassword().equals(member.getPassword())){
            System.out.println("Admin Dashboard");
            return "redirect:/dashboard";
        }
        else if(memberCheck != null && memberCheck.getPassword().equals(member.getPassword())){
            System.out.println("User Dashboard");
            loginMember = memberService.getByEmail(member.getEmail());
            return "redirect:/userDashboard";//user er jonno alada dashboard create kre oitate redirect krte hbe
        }

        return "redirect:/?error=true";
    }

    @GetMapping("/signout")
    public String signOut(Model model){
        model.addAttribute("member", new Member());
        return "redirect:/index";
    }


//    @GetMapping("/adminManageBorrow")
//    public String borrowing(Model model){
//        ErrorPageSupport bookService;
//        model.addAttribute("bookList", bookService.findAll());
//        model.addAttribute("totalUser", memberService.getAll().size());
//        List<Book> bookList = bookService.findAll();
//
//        int totalCopies = bookList.stream().mapToInt(Book::getNumberOfCopies).sum();
//        model.addAttribute("totalBooks", totalCopies);
//
//        return "adminManageBorrowing";
//    }


    @PostMapping("/bookManageTask")
    public String objectSubmitForm(@RequestParam String query, Model model){
        if(query == null || query.isEmpty()){
            model.addAttribute("bookList", new ArrayList<>());
        }

        List<Book> foundBooks = bookService.searchBooks(query); //member search er method hbe, user ki boi nise map kre niye ashte hbe

        model.addAttribute("bookList", foundBooks);

        return "dashboard";
    }

    @GetMapping("/adminManageBorrow")
    public String page(Model model){
        model.addAttribute("memberList", borrowRecordService.GetingUsersWhomHaventReturnedBook());
        model.addAttribute("member", new Member()); //ei line theke start hbe
//        model.addAttribute("totalUser", memberService.getAll().size());
//        List<Book> bookList = bookService.findAll();

//        int totalCopies = bookList.stream().mapToInt(Book::getNumberOfCopies).sum();
//        model.addAttribute("totalBooks", totalCopies);



        return "adminManageBorrowing";
    }

    @PostMapping("/borrow/edit")
    public String showBorrowedBooks( Model model, @ModelAttribute Member member ) {
        int id = member.getId().intValue();

        Member selectedMember = memberService.findById(member.getId());
        List<BookCopy> borrowedBooks = borrowRecordService.listOfBooksTakenByMember(member.getId());


        model.addAttribute("member", selectedMember);
        model.addAttribute("memberList", borrowRecordService.GetingUsersWhomHaventReturnedBook());
        model.addAttribute("books", borrowRecordService.listOfBooksTakenByMember(member.getId()));
//        System.out.println(member.getId());
//        for(Book i: borrowRecordService.GettingBooksBorrowedByMember(member.getId())){
//            System.out.println(i.getTitle());
//        }
//        System.out.println(borrowRecordService.GettingBooksBorrowedByMember(member.getId()).size());

        return "adminManageBorrowing";
    }




    //Borrow edit er jonno nicher method ta edit krte hbe


//    @PostMapping("/userEdit/submit")
//    public String objectSubmitForm2(@ModelAttribute Member member, Model model) {
//
//        int id = member.getId().intValue();
//        Member existing = memberService.findById(id);
//
//        if (existing == null) {
//            model.addAttribute("error", "No member found with ID: " + member.getId());
//            return "redirect:/adminUserEdit";
//        }
//
//
//        existing.setName(member.getName());
//        existing.setEmail(member.getEmail());
//        existing.setMobileNumber(member.getMobileNumber());
//
//
//        memberService.save(existing);
//
//        return "redirect:/adminUserEdit";
//    }



    @GetMapping("/adminUserEdit")
    public String page2(Model model){
        model.addAttribute("memberList", memberService.getAll()); //ei line theke start hbe
        model.addAttribute("member", new Member());
//        model.addAttribute("totalUser", memberService.getAll().size());
//        List<Book> bookList = bookService.findAll();

//        int totalCopies = bookList.stream().mapToInt(Book::getNumberOfCopies).sum();
//        model.addAttribute("totalBooks", totalCopies);

        return "adminUserEdit";
    }


}
