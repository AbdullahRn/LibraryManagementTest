package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Admin;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.service.AdminService;
import bd.edu.seu.examlibrarymanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    private final MemberService memberService;
    private final AdminService adminService;

    public WebController(MemberService memberService, AdminService adminService) {
        this.memberService = memberService;
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("member", new Member());
        return "index"; // Returns dashboard.html from templates/
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("member", new Member());
        return "index"; // Returns dashboard.html from templates/
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
            return "redirect:/userDashboard";//user er jonno alada dashboard create kre oitate redirect krte hbe
        }

        return "redirect:/?error=true";
    }

    @GetMapping("/signout")
    public String signOut(Model model){
        model.addAttribute("member", new Member());
        return "redirect:/index"; // Returns dashboard.html from templates/
    }
}
