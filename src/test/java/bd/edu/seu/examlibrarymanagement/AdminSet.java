package bd.edu.seu.examlibrarymanagement;

import bd.edu.seu.examlibrarymanagement.model.Admin;
import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class AdminSet {

    @Autowired
    private AdminService adminService;

    @Test
    public void AdminSetter(){
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        adminService.save(admin);
    }
}
