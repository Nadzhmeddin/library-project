package ru.project.library.enums;

public enum ReadingStatus {

    NOT_STARTED("not_started"),
    IN_PROGRESS("in_progress"),
    COMPLETED("completed"),
    ABANDONED("abandoned");

    private String readingStatus;

    ReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public String getReadingStatus() {
        return readingStatus;
    }
}
