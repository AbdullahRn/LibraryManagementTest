package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.service.BookService;
import bd.edu.seu.examlibrarymanagement.service.BorrowRecordService;
import bd.edu.seu.examlibrarymanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminMemberCreateController {


    private final BookService bookService;
    private final MemberService memberService;
    private final BorrowRecordService borrowRecordService;

    public AdminMemberCreateController(BookService bookService, MemberService memberService, BorrowRecordService borrowRecordService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.borrowRecordService = borrowRecordService;
    }


    @PostMapping("/userEdit/submit")
    public String objectSubmitForm2(@ModelAttribute Member member, Model model) {

        int id = member.getId().intValue();
        Member existing = memberService.findById(id);

        if (existing == null) {
            model.addAttribute("error", "No member found with ID: " + member.getId());
            return "redirect:/adminUserEdit";
        }


        existing.setName(member.getName());
        existing.setEmail(member.getEmail());
        existing.setMobileNumber(member.getMobileNumber());


        memberService.save(existing);

        return "redirect:/adminUserEdit";
    }

}
