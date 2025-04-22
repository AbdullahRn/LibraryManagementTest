package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import bd.edu.seu.examlibrarymanagement.repository.BookCopyRepository;
import bd.edu.seu.examlibrarymanagement.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookCopyRepository bookCopyRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BookCopyRepository bookCopyRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    private final int allowedMaxBooks = 2;
    private final int maxDaysAllowed = 7;
    private final double penaltyPerDay = 25.0;








}
