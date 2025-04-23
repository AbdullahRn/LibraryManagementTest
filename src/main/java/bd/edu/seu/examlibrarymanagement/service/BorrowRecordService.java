package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import bd.edu.seu.examlibrarymanagement.model.BorrowRecord;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.repository.BookCopyRepository;
import bd.edu.seu.examlibrarymanagement.repository.BookRepository;
import bd.edu.seu.examlibrarymanagement.repository.BorrowRecordRepository;
import bd.edu.seu.examlibrarymanagement.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookCopyRepository bookCopyRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BookCopyRepository bookCopyRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    private final int allowedMaxBooks = 2;
    private final int maxDaysAllowed = 7;
    private final double penaltyPerDay = 25.0;

//    public Optional<BookCopy> findFirstBookCopy(String bookId) {
//        Optional<BookCopy> book = bookCopyRepository.findFirstByBookIdAndAvailableTrue(bookId);
//        return book;
//    }

    public BorrowRecord BorrowBook(int memberId, int bookId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        int currentlyBorrowedBooks = borrowRecordRepository.countByMemberAndReturnedFalse(member);

        BookCopy bookCopy = bookCopyRepository.findFirstByBookIdAndAvailableTrue(bookId).orElse(null);

        if(currentlyBorrowedBooks >= allowedMaxBooks) {
            return null;
        }
        //controller e check krbo jodi null return kre tahole msg prompt hbe je boi nite parbena, or maximum limit reached

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setMember(member);
        borrowRecord.setBookCopy(bookCopy);
        borrowRecord.setDueDate(LocalDate.now().plusDays(maxDaysAllowed));
        borrowRecord.setReturned(false);
        return borrowRecordRepository.save(borrowRecord);


    }

    public BorrowRecord ReturnBook(int borrowerId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowerId).orElse(null);

        if(borrowRecord.isReturned()){
            return borrowRecord;
        }

        borrowRecord.setReturned(true);
        long days = ChronoUnit.DAYS.between(borrowRecord.getDueDate(), LocalDate.now());
        if(days > maxDaysAllowed){
            borrowRecord.setLateFee(days * penaltyPerDay);
        }

        BookCopy copy = borrowRecord.getBookCopy();
        copy.setAvailable(true);
        bookCopyRepository.save(copy);
        return borrowRecordRepository.save(borrowRecord);


    }

    public List<BookCopy> listOfBooksTakenByMember(Long memberId) {
        int intValue = memberId.intValue();
        Member member = memberRepository.findById(intValue).orElse(null);

        List<BorrowRecord> borrowRecords = borrowRecordRepository.findByMember(member);

        return borrowRecords.stream().map(BorrowRecord::getBookCopy).collect(Collectors.toList());
    }








}
