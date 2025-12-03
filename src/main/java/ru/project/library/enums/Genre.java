package ru.project.library.enums;

public enum Genre {

    FANTASY("fantasy"),
    DETECTIVE("detective"),
    HISTORICAL("historical"),
    SCIENTIFIC("scientific");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
