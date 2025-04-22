package bd.edu.seu.examlibrarymanagement.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String isbn;
    private int numberOfCopies;
    private LocalDate publicactionYear;
    private String Genere;



    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_authors", joinColumns = @JoinColumn(name = "author_name"))
    private List<String> authors;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public LocalDate getPublicactionYear() {
        return publicactionYear;
    }

    public void setPublicactionYear(LocalDate publicactionYear) {
        this.publicactionYear = publicactionYear;
    }

    public String getGenere() {
        return Genere;
    }

    public void setGenere(String genere) {
        Genere = genere;
    }
}
