package ru.project.library.dto;

import ru.project.library.enums.Genre;
import ru.project.library.enums.ReadingStatus;

public class BookDto {

    private String title;
    private Long authorId;
    private String genre;
    private Long userId;
    private Integer publicationYear;
    private ReadingStatus status;

    public BookDto() {
    }

    public BookDto(String title, Long authorId, String genre, Long userId, Integer publicationYear, ReadingStatus status) {
        this.title = title;
        this.authorId = authorId;
        this.genre = genre;
        this.userId = userId;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
