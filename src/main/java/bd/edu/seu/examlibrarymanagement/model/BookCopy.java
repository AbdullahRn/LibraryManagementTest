package bd.edu.seu.examlibrarymanagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int isbn;

    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "bookCopy")
    private List<BorrowRecord> borrowRecord = new ArrayList<>();




}
