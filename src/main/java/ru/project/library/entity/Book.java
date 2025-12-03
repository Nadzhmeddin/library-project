package ru.project.library.entity;

import jakarta.persistence.*;
import ru.project.library.enums.Genre;
import ru.project.library.enums.ReadingStatus;

@Entity
@Table(name = "books_tb")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Integer publicationYear;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    public Book() {
    }

    public Book(String title, Author author, MyUser user, Genre genre, Integer publicationYear, ReadingStatus status) {
        this.title = title;
        this.author = author;
        this.user = user;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public ReadingStatus getStatus() {
        return status;
    }

    public void setStatus(ReadingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", user=" + user +
                ", genre=" + genre +
                ", publicationYear=" + publicationYear +
                ", status=" + status + "\n";
    }
}
