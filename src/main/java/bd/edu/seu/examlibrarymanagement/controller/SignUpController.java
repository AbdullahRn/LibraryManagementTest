package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    private final MemberService memberService;

    public SignUpController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public String objectSubmitForm(Model model) {
        model.addAttribute("member", new Member());
        System.out.println("Hello!!!!");
        return "signuppage";
    }


    @PostMapping("/signup/submit")
    public String objectSubmitForm1(@ModelAttribute Member member){

        Member memberGot =memberService.getByEmail(member.getEmail());
        if(memberGot != null && memberGot.getEmail().equals(member.getEmail())){
            return "redirect:/signup?error=true";
        }

        memberService.save(member);
        return "redirect:/dashboard";
    }


}
