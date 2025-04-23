package bd.edu.seu.examlibrarymanagement.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int isbn;

    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "bookCopy")
    private List<BorrowRecord> borrowRecord = new ArrayList<>();


    public int getIsbn() {
        return isbn;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<BorrowRecord> getBorrowRecord() {
        return borrowRecord;
    }

    public void setBorrowRecord(List<BorrowRecord> borrowRecord) {
        this.borrowRecord = borrowRecord;
    }
}
