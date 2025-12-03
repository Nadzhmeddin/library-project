package ru.project.library.dto;

import ru.project.library.enums.Genre;
import ru.project.library.enums.ReadingStatus;

public class BookDto {

    private String title;
    private Long authorId;
    private Genre genre;
    private Long user_id;
    private Integer publicationYear;
    private ReadingStatus status;

    public BookDto() {
    }

    public BookDto(String title, Long authorId, Genre genre, Long user_id, Integer publicationYear, ReadingStatus status) {
        this.title = title;
        this.authorId = authorId;
        this.genre = genre;
        this.user_id = user_id;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
}
