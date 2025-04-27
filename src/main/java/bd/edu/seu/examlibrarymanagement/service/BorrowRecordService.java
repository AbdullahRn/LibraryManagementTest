package bd.edu.seu.examlibrarymanagement.service;

import bd.edu.seu.examlibrarymanagement.model.Book;
import bd.edu.seu.examlibrarymanagement.model.BookCopy;
import bd.edu.seu.examlibrarymanagement.model.BorrowRecord;
import bd.edu.seu.examlibrarymanagement.model.Member;
import bd.edu.seu.examlibrarymanagement.repository.BookCopyRepository;
import bd.edu.seu.examlibrarymanagement.repository.BookRepository;
import bd.edu.seu.examlibrarymanagement.repository.BorrowRecordRepository;
import bd.edu.seu.examlibrarymanagement.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static bd.edu.seu.examlibrarymanagement.controller.DashboardController.loginMember;

@Service
public class BorrowRecordService{

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

    public void BorrowBook(int memberId, int bookId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        int currentlyBorrowedBooks = borrowRecordRepository.countByMemberAndReturnedFalse(member);

        BookCopy bookCopy = bookCopyRepository.findFirstByBookIdAndAvailableTrue(bookId).orElse(null);

        if(currentlyBorrowedBooks >= allowedMaxBooks) {
            return;
        }
        //controller e check krbo jodi null return kre tahole msg prompt hbe je boi nite parbena, or maximum limit reached

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setMember(member);
        borrowRecord.setBookCopy(bookCopy);
        borrowRecord.setDueDate(LocalDate.now().plusDays(maxDaysAllowed));
        borrowRecord.setReturned(false);
        borrowRecordRepository.save(borrowRecord);

        bookCopy.setAvailable(false);
        bookCopyRepository.save(bookCopy);


    }

    public BorrowRecord returnBook(int id, int memberId) {
        BorrowRecord borrowRecord = borrowRecordRepository.findByBookCopyIsbnAndMemberId(id,memberId).orElse(null);

        if (borrowRecord != null && borrowRecord.isReturned()){
            return borrowRecord;
        }

        BookCopy copy = borrowRecord.getBookCopy();
        copy.setAvailable(true);
        bookCopyRepository.save(copy);
        borrowRecord.setReturned(true);
        borrowRecord.setLateFee(0.0);
        borrowRecord.setReturnDate(LocalDate.now());
        return borrowRecordRepository.save(borrowRecord);


    }
    @PostConstruct
    public void updateLateFee(){
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findByReturnedFalse();

        for(BorrowRecord borrowRecord : borrowRecords){
            long daysOverDue = ChronoUnit.DAYS.between(borrowRecord.getDueDate(), LocalDate.now());
            if(daysOverDue > maxDaysAllowed){
                borrowRecord.setLateFee(daysOverDue * penaltyPerDay);
            }
            else{
                borrowRecord.setLateFee(0.0);
            }
            borrowRecordRepository.save(borrowRecord);
        }


    }

    public List<BookCopy> listOfBooksTakenByMember(Integer memberId) {
        int intValue = memberId.intValue();
        Member member = memberRepository.findById(intValue).orElse(null);

        List<BorrowRecord> borrowRecords = borrowRecordRepository.findByMember(member);

        return borrowRecords.stream().map(BorrowRecord::getBookCopy).collect(Collectors.toList());
    }

    public List<Member> GetingUsersWhomHaventReturnedBook(){
        List<BorrowRecord> list = borrowRecordRepository.findDistinctByReturnedFalse();

        return list.stream().map(BorrowRecord::getMember).distinct().collect(Collectors.toList());
    }

    public List<Book> GettingBooksBorrowedByMember(Integer memberId) {
        int id = memberId.intValue();
        List<BorrowRecord> list = borrowRecordRepository.findDistinctMembersByReturnedFalseAndId(id);

        List<Book> bookList = new ArrayList<>();
        for(BorrowRecord borrowRecord : list){
            Optional<BookCopy> book = bookCopyRepository.findByBookId(borrowRecord.getId());
            bookList.add(book.get().getBook());
        }
        return bookList;


    }








}
