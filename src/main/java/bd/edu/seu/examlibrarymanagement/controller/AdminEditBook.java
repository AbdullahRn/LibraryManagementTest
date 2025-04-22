package bd.edu.seu.examlibrarymanagement.controller;

import bd.edu.seu.examlibrarymanagement.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminEditBook {
    @GetMapping("/adminBookEdit")
    public String home(Model model){
        model.addAttribute("member", new Member());
        return "adminbookedit"; // Returns dashboard.html from templates/
    }
}
