package bd.edu.seu.examlibrarymanagement;

import bd.edu.seu.examlibrarymanagement.service.BorrowRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExamLibraryManagementApplicationTests {
    @Autowired
    BorrowRecordService borrowRecordService;

//    @Test
//    void contextLoads() {
//        borrowRecordService.returnBook(8);
//
//    }

}
