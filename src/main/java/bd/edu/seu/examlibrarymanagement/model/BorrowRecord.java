package bd.edu.seu.examlibrarymanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private boolean isReturned = false;
    private double lateFee = 0.0;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;



}
